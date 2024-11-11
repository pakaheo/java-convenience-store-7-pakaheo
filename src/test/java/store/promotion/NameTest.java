package store.promotion;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    void 프로모션_이름_생성() {
        Name name = new Name("탄산2+1");

        assertThat(name).isEqualTo(new Name("탄산2+1"));
    }
}
