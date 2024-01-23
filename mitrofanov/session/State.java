package mitrofanov.session;

import lombok.Getter;

@Getter
public enum State {
    IDLE("/idle"),
    START("/start"),
    BUTTON("/button"),
    START_NICKNAME("/start_nickname"),
    START_RACE("/start_race"),
    PROFILE("/profile"),
    TRAINING("/training"),
    BADALKA("/badalka"),
    FARM("/farm"),
    THREE_HOURS("/threeHours"),
    SIX_HOURS("/sixHours"),
    TWELVE_HOURS("/twelveHours"),
    HOURS("/hours");

    private String value;

    State(String value) {
        this.value = value;
    }
}
