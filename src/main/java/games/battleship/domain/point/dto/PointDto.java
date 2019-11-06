package games.battleship.domain.point.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class PointDto {
    private final int row;
    private final int column;    
}
