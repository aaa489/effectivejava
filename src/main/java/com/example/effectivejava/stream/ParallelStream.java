package com.example.effectivejava.stream;

/**
 *《effective java》-48：谨慎使用流并行
 * 即使在最好的情况下，如果源来自 Stream.iterate 方法，或者使用中间操作 limit 方法，并行化管道也不太可能提高其性能
 * 通常，并行性带来的性能收益在 ArrayList 、 HashMap 、 HashSet 和 ConcurrentHashMap 实例、数组、 int 类型范围和 long 类型的范围的流上最好
 * 并行化一个流不仅会导致糟糕的性能，包括活性失败（liveness failures）;它会导致不正确的结果和不可预知的行为 (安全故障)。
 * 不要尝试并行化流管道，除非你有充分的理由相信它将保持计算的正确性并提高其速度
 * 如果要并行化随机数流，请从 SplittableRandom 实例开始，而不是 ThreadLocalRandom （或基本上过时的 Random ）
 * @author Don
 * @date 2022/2/28.
 */
public class ParallelStream {

}
