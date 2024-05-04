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
class CardRegisterAdapterRouterTest {

    @Test
    void 카드_등록_어뎁터_라우터_성공_테스트() {
        CardRegisterAdapter cardRegisterAdapter = mock(CardRegisterAdapter.class);
        when(cardRegisterAdapter.getCardPaymentAgency()).thenReturn(CardPaymentAgency.NICEPAY);

        CardRegisterAdapterRouter cardRegisterAdapterRouter = new CardRegisterAdapterRouter(
            Arrays.asList(cardRegisterAdapter)
        );


        CardRegisterAdapter adapter = cardRegisterAdapterRouter.route(CardPaymentAgency.NICEPAY);

        Assertions.assertThat(adapter).isNotNull();
    }

    @Test
    void 카드_등록_어뎁터_라우터_실패_테스트() {
        CardRegisterAdapter cardRegisterAdapter = mock(CardRegisterAdapter.class);
        when(cardRegisterAdapter.getCardPaymentAgency()).thenReturn(CardPaymentAgency.NICEPAY);

        CardRegisterAdapterRouter cardRegisterAdapterRouter = new CardRegisterAdapterRouter(
            Arrays.asList(cardRegisterAdapter)
        );


        Assertions.assertThatThrownBy(() -> cardRegisterAdapterRouter.route(CardPaymentAgency.KAKAO))
            .isInstanceOf(CardRegisterAdapterNotFoundException.class);
    }
}