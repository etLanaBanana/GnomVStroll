package mitrofanov.model.repository;

import lombok.SneakyThrows;
import mitrofanov.model.db.DBConnection;
import mitrofanov.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class FermaRepository {
    public void saveThisUsersTime(User user, int hours) {
        try {
            Connection connection = DBConnection.getConnection();
            String sql = "INSERT INTO playerTime (chat_id, date_plus_hours) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, user.getChatId());
            statement.setObject(2, hours);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при записи даты");
        }
    }
    @SneakyThrows
    public static LocalDateTime getThisUserTime(String chatId) {
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT date_plus_hours FROM player WHERE chat_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, chatId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getObject("date_plus_hours", LocalDateTime.class);
        }
        return null;
    }
    public static void updateUserTime(String chatId, LocalDateTime hours) {
        try {
            Connection connection = DBConnection.getConnection();
            String sql = "UPDATE player SET date_plus_hours = ? WHERE chat_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, hours);
            statement.setString(2, chatId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка");
        }
    }
    public static void addGoldForUser(String chatId, int gold) {
        try {
            Connection connection = DBConnection.getConnection();
            String sql = "UPDATE player SET gold = gold + ? WHERE chat_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, gold);
            statement.setString(2, chatId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка");
        }
    }
}
