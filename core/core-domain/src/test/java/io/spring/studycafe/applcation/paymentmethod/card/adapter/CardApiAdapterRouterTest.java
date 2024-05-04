package io.spring.studycafe.applcation.paymentmethod.card.adapter;

import io.spring.studycafe.domain.paymentmethod.card.CardPaymentAgency;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.annotation.Import;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@Import(Mockito.class)
class CardApiAdapterRouterTest {

    @Test
    void 카드_등록_어뎁터_라우터_성공_테스트() {
        CardApiAdapter cardApiAdapter = mock(CardApiAdapter.class);
        when(cardApiAdapter.getCardPaymentAgency()).thenReturn(CardPaymentAgency.NICEPAY);

        CardApiAdapterRouter cardApiAdapterRouter = new CardApiAdapterRouter(
            Arrays.asList(cardApiAdapter)
        );


        CardApiAdapter adapter = cardApiAdapterRouter.route(CardPaymentAgency.NICEPAY);

        Assertions.assertThat(adapter).isNotNull();
    }

    @Test
    void 카드_등록_어뎁터_라우터_실패_테스트() {
        CardApiAdapter cardApiAdapter = mock(CardApiAdapter.class);
        when(cardApiAdapter.getCardPaymentAgency()).thenReturn(CardPaymentAgency.NICEPAY);

        CardApiAdapterRouter cardApiAdapterRouter = new CardApiAdapterRouter(
            Arrays.asList(cardApiAdapter)
        );


        Assertions.assertThatThrownBy(() -> cardApiAdapterRouter.route(CardPaymentAgency.KAKAO))
            .isInstanceOf(CardApiAdapterNotFoundException.class);
    }
}