package jpa.jpa_shop.web.controller.API;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class ProfileApiControllerTest {

    @Test
    public void real_profileTest(){
        // given
        String expectedProfile = "real";
        MockEnvironment mockEnvironment = new MockEnvironment();
        mockEnvironment.addActiveProfile(expectedProfile);
        mockEnvironment.addActiveProfile("real-db");
        ProfileApiController profileApiController = new ProfileApiController(mockEnvironment);

        // when
        String profile = profileApiController.profile();
        // then
        assertThat(profile).isEqualTo(expectedProfile);
    }
    /*
    real이라는 profile 이 없다면 첫번쨰가 조회되는 test
    */
    @Test
    public void notExistDefaultProfileTest(){
        // given
        String expectedProfile = "profile1";
        MockEnvironment mockEnvironment = new MockEnvironment();
        mockEnvironment.addActiveProfile(expectedProfile);
        mockEnvironment.addActiveProfile("real-dev");
        ProfileApiController profileApiController = new ProfileApiController(mockEnvironment);

        // when
        String profile = profileApiController.profile();

        // then
        assertThat(profile).isEqualTo(expectedProfile);
    }
    /*
    활성화된 profile이 없으면 default 가 선택되는 test
    */
    @Test
    public void defaultTest(){
        // given
        String expectedProfile = "default";
        MockEnvironment mockEnvironment = new MockEnvironment();
        ProfileApiController profileApiController = new ProfileApiController(mockEnvironment);

        // when
        String profile = profileApiController.profile();

        // then
        assertThat(profile).isEqualTo(expectedProfile);
    }
}