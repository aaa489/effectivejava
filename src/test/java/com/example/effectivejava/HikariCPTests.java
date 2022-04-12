package com.example.effectivejava;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;

import static com.zaxxer.hikari.util.UtilityElf.IS_JAVA7;

/**
 * @author Don
 * @date 2022/3/15.
 */
public class HikariCPTests {

    AtomicLong atomicLong = new AtomicLong();
    long startSeq = atomicLong.get();
    AtomicLong sequence = new AtomicLong(1);
    AbstractQueuedLongSynchronizer synchronizer1 = new Synchronizer();
    @Test
    void testLock() {
      Thread t1 = new Thread(() -> {
          try{
              //尝试获取信号量
            synchronizer1.tryAcquireSharedNanos(startSeq, TimeUnit.MILLISECONDS.toNanos(10000));
            System.out.println("got!");
         }
         catch (Exception ex){

         }
      });

      Thread t2 = new Thread(() -> {
          //释放一个信号量
          synchronizer1.releaseShared(1);
          try{
              Thread.sleep(500);
          }
          catch (Exception ex){

          }
          //是否有其他线程在等待
         if (synchronizer1.hasQueuedThreads()){
            System.out.println("abc");
         }
         else {
            System.out.println("bcd");
         }
      });

      t1.start();
      try{
         Thread.sleep(1000);
      }
      catch (Exception ex){

      }
      t2.start();

      try {
         Thread.sleep(3000000);
      }
      catch (Exception ex){

      }
    }

    private final class Synchronizer extends AbstractQueuedLongSynchronizer
    {
        private static final long serialVersionUID = 104753538004341218L;

        @Override
        protected long tryAcquireShared(final long seq)
        {
            return java67hasQueuedPredecessors() ? -1L : getState() - (seq + 1);
        }

        /** {@inheritDoc} */
        @Override
        protected boolean tryReleaseShared(final long ignored)
        {
            setState(sequence.get());

            return true;
        }

        private boolean java67hasQueuedPredecessors()
        {
            if (IS_JAVA7) {
                return hasQueuedPredecessors();
            }

            return false;
        }
    }

}
