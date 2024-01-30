package mitrofanov.session;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Session {
    private Long sessionID;
    private State state;
}