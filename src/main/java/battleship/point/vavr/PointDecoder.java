package battleship.point.vavr;

import org.apache.commons.lang3.StringUtils;

import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Try;

public final class PointDecoder {
	public static final List<Character> chars = List.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
	        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'W', 'X', 'Y', 'Z');

	public static Either<String, Integer> getRow(String pointString) {
		if (StringUtils.isEmpty(pointString)) {
			return Either.left("String cannot be empty");
		}
		String posX = pointString.substring(1);
		Try<Integer> i = Try.of(() -> Integer.parseInt(posX));
		return i.isSuccess() ? Either.right(i.get() - 1) : Either.left("You have to set Row position");
	}

	public static Either<String, Integer> getColumn(String pointString) {
		if (StringUtils.isEmpty(pointString)) {
			return Either.left("String cannot be empty");
		}
		pointString = pointString.toUpperCase();
		char posY = pointString.charAt(0);
		int y = chars.indexOf(Character.valueOf(posY));
		return y == -1 ? Either.left("Wrong column specified") : Either.right(y);
	}

	public static Either<String, String> pointToString(Point point) {
		Try<Character> tryCharacter = Try.of(() -> chars.get(point.getColumn()));
		if (tryCharacter.isFailure()) {
			return Either.left("Cannot find Column for this point");
		}
		StringBuilder builder = new StringBuilder();
		builder.append(tryCharacter.get());
		builder.append(point.getRow() + 1);
		return Either.right(builder.toString());
	}
}
