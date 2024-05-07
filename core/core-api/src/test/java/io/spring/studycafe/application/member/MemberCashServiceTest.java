package io.spring.studycafe.application.member;

import io.spring.studycafe.applcation.member.MemberCashRechargeService;
import io.spring.studycafe.applcation.member.MemberCashUseService;
import io.spring.studycafe.applcation.member.MemberSearchService;
import io.spring.studycafe.domain.member.Member;
import io.spring.studycafe.domain.member.MemberRepository;
import io.spring.studycafe.domain.member.RegistrationPlatform;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@DisplayName("회원 캐시 관련 테스트")
@ActiveProfiles("test")
@SpringBootTest
public class MemberCashServiceTest {


    @Autowired
    private MemberCashRechargeService memberCashRechargeService;

    @Autowired
    private MemberCashUseService memberCashUseService;

    @Autowired
    private MemberSearchService memberSearchService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void test() {
        Member member = memberRepository.save(new Member("", "", RegistrationPlatform.KAKAO));

        memberCashRechargeService.rechargeCash(member.getId(), 10000);

        memberCashUseService.useCash(member.getId(), 1000L);

        Member findMember = memberSearchService.getById(member.getId());

        Assertions.assertThat(findMember.getMemberCash().getCash()).isEqualTo(10000 - 1000);
    }

    @Test
    public void 회원_캐시가_동시에_10000번_차감되었을때_갱신손실이_발생하지않는지_테스트() throws InterruptedException, ExecutionException {
        final long remainingAmount = 100000;
        final long usageAmount = 1;
        final int numberOfThreads = 10000;

        Member member = memberRepository.save(new Member("test@test", "tester", RegistrationPlatform.KAKAO));
        memberCashRechargeService.rechargeCash(member.getId(), remainingAmount);

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(1);
        List<Future<String>> futures = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            final int threadNumber = i;
            Callable<String> task = () -> {
                latch.await(); // 모든 스레드가 준비될 때까지 대기

                // 여기에 자원을 사용하는 로직을 추가
                memberCashUseService.useCash(member.getId(), usageAmount);
                return "스레드 " + threadNumber + ": 성공";
            };
            futures.add(executorService.submit(task));
        }

        latch.countDown(); // 모든 스레드에 실행을 시작하라는 신호를 보냄
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);

        // 모든 스레드의 결과를 확인
        for (Future<String> future : futures) {
            System.out.println(future.get());
        }

        Member findMember = memberSearchService.getById(member.getId());
        Assertions.assertThat(findMember.getMemberCash().getCash()).isEqualTo(remainingAmount - (numberOfThreads * usageAmount));
    }

    @Test
    public void 회원_캐시가_동시에_10000번_충전되었을때_갱신손실이_발생하지않는지_테스트() throws InterruptedException, ExecutionException {
        final long remainingAmount = 100000;
        final long rechargeAmount = 1;
        final int numberOfThreads = 10000;

        Member member = memberRepository.save(new Member("test2@test", "tester", RegistrationPlatform.KAKAO));
        memberCashRechargeService.rechargeCash(member.getId(), remainingAmount);

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(1);
        List<Future<String>> futures = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            final int threadNumber = i;
            Callable<String> task = () -> {
                latch.await(); // 모든 스레드가 준비될 때까지 대기

                // 여기에 자원을 사용하는 로직을 추가
                memberCashRechargeService.rechargeCash(member.getId(), rechargeAmount);
                return "스레드 " + threadNumber + ": 성공";
            };
            futures.add(executorService.submit(task));
        }

        latch.countDown(); // 모든 스레드에 실행을 시작하라는 신호를 보냄
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);

        // 모든 스레드의 결과를 확인
        for (Future<String> future : futures) {
            System.out.println(future.get());
        }

        Member findMember = memberSearchService.getById(member.getId());
        Assertions.assertThat(findMember.getMemberCash().getCash()).isEqualTo(remainingAmount + (numberOfThreads * rechargeAmount));

    }
}
