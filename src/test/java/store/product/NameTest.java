package store.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    void 이름_생성() {
        Name name = new Name("감자칩");

        assertThat(name).isEqualTo(new Name("감자칩"));
    }

    @Test
    void 이름_비교() {
        Name name = new Name("감자칩");
        
        assertThat(name.match("감자칩")).isTrue();
    }
}
