package racingcar.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class RacingCarGameRequestDto {

    @NotBlank(message = "플레이어의 이름은 빈 값이 될 수 없습니다.")
    private final String names;

    @Min(value = 1, message = "시도횟수는 빈 값이 될 수 없습니다.")
    private final Integer count;

    public RacingCarGameRequestDto(String names, int count) {
        this.names = names;
        this.count = count;
    }

    public String getNames() {
        return names;
    }

    public int getCount() {
        return count;
    }
}
