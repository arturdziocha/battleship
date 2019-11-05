package battleship.domain.point;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
class Point {
    private final int row;
    private final int column;
}
