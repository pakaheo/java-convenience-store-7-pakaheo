package store;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class MembershipTest {

    @Test
    void 멤버십_할인_금액_계산() {
        assertThat(MemberShip.apply(1_000, 15)).isEqualTo(4_500);
        assertThat(MemberShip.apply(2_000, 14)).isEqualTo(8_000);
    }
}
