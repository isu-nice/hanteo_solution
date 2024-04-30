package hanteo.solution.question2;

import hanteo.solution.question2.exception.ExceptionMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CoinGameTest {

    private CoinGame game;

    @BeforeEach
    void before() {
        game = new CoinGame();
    }

    @Test
    @DisplayName("성공 케이스 1")
    void successCase1() {
        int sum = 4;
        int[] coins = {1, 2, 3};

        int result = game.countSumCombinations(sum, coins);

        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("성공 케이스 2")
    void successCase2() {
        int sum = 10;
        int[] coins = {2, 5, 3, 6};

        int result = game.countSumCombinations(sum, coins);

        assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("합계가 0인 경우 - IllegalArgumentException 발생")
    void failCase_Sum1() {
        int sum = 0;
        int[] coins = {1, 3, 5};

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> game.countSumCombinations(sum, coins));

        assertThat(exception.getMessage()).isEqualTo(ExceptionMessage.INVALID_SUM.getMessage());
    }

    @Test
    @DisplayName("합계가 음수인 경우 - IllegalArgumentException 발생")
    void failCase_Sum2() {
        int sum = -15;
        int[] coins = {19, 2, 3, 1};

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> game.countSumCombinations(sum, coins));

        assertThat(exception.getMessage()).isEqualTo(ExceptionMessage.INVALID_SUM.getMessage());
    }

    @Test
    @DisplayName("1보다 작은 동전이 있는 경우 - IllegalArgumentException 발생")
    void failCase_Coins() {
        int sum = 5;
        int[] coins = {-1, 1, 0};

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> game.countSumCombinations(sum, coins));

        assertThat(exception.getMessage()).isEqualTo(ExceptionMessage.INVALID_COINS.getMessage());
    }

    @Test
    @DisplayName("동전의 최소값보다 합계가 작은 경우 - - IllegalArgumentException 발생")
    void failCase3() {
        int sum = 5;
        int[] coins = {7, 9, 11};

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> game.countSumCombinations(sum, coins));

        assertThat(exception.getMessage()).isEqualTo(ExceptionMessage.INVALID_MIN_COIN.getMessage());
    }
}