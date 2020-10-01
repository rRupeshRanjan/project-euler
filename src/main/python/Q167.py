import time

def answer():
    sum_ulam = get_ulam(5, 10 ** 11)
    i = 7
    while i <= 21:
        sum_ulam += get_ulam(i, 10 ** 11)
        i += 2
    return sum_ulam


def get_ulam(y, k):
    ulam = get_ulam_2(y)
    if k <= len(ulam[2][0]):
        return ulam[2][0][k - 1]
    shift = k - len(ulam[2][0])
    remainder = shift % ulam[0]

    # doesn't work for some reason when remainder = 0
    if remainder == 0:
        remainder = len(ulam[2][1])
    return ulam[2][1][remainder - 1] + (shift - remainder) * ulam[1] / ulam[0]


def get_ulam_2(y):
    p, seq = get_period_first_difference(2, y)
    repeated_seq = get_repeated_sequence(p, seq)
    first_difference = get_first_difference(p, seq)
    return p, first_difference, repeated_seq


def get_repeated_sequence(period, seq):
    even_index = find_even(seq, 2)
    return seq[:even_index + 1], seq[even_index + 1:even_index + period + 1]


def get_first_difference(period, seq):
    even_index = find_even(seq, 2)
    return seq[even_index + 1 + period] - seq[even_index + 1]


def get_period_first_difference(x, y):
    if x != 2 or y < 5:
        return ["No period exists."]

    n = 100

    while True:
        seq = ulam_2(y, n)
        even_index = find_even(seq, 2)
        seq_diff = format_sequence(seq)[even_index + 1:]
        p = period(seq_diff)
        if p:
            return p, seq
        n *= 10

        # play with the line below to change time out length
        if n >= 100000000:
            break
    return ["No period found. Try increasing timeout."], seq


def ulam_2(y, length):
    # sequence, candidates, even index, index - 1 of largest term so far
    seq, cand, even_index, n = next_even([2, y, 2 + y])
    while n < length:
        cand.append(seq[0] + seq[n - 1])
        cand.append(seq[even_index] + seq[n - 1])
        cand.sort()
        # since list is sorted, only need beginning of list
        while cand[0] in cand[1:2]:
            temp = cand[0]
            cand.pop(0)
            cand.pop(0)
            if cand[0] == temp and cand[0] != cand[1]:
                cand.pop(0)
        seq.append(cand[0])
        cand.pop(0)
        n += 1
    return seq


def next_even(seq):
    cand = []
    n = len(seq)
    # want to generate 1 beyond the 2nd even, thus n-2
    while seq[n - 2] % 2 or n - 2 == 0:
        for e in seq[:n - 1]:
            cand.append(e + seq[n - 1])
        cand.sort()
        # since list is sorted, only need to check first 2 terms
        while cand[0] in cand[1:2]:
            temp = cand[0]
            cand.pop(0)
            cand.pop(0)
            if cand[0] == temp and cand[0] != cand[1]:
                cand.pop(0)
        seq.append(cand[0])
        cand.pop(0)
        n += 1
    # now we get rid of even candidates

    i = 0
    while i < len(cand):
        if cand[i] % 2 == 0:
            cand.pop(i)
        else:
            i += 1
    return seq, cand, n - 2, n


def format_sequence(sequence):
    formatted_sequence = []
    previous = sequence[0]
    for e in sequence[1:]:
        formatted_sequence.append(e - previous)
        # current e becomes previous e next iteration
        previous = e
    return formatted_sequence


def period(seq):
    unique = []
    last = 0
    for index, e in enumerate(seq):
        if e not in unique:
            unique.append(e)
            last = index
    # this is the smallest sequence that captures all unique values
    min_pattern = seq[:last]

    # is the sequence long enough to test for a repeat?
    if len(seq) < 2 * len(min_pattern):
        return []

    min_pattern = seq[:last]
    for index, e in enumerate(seq[last:]):
        if e == min_pattern[0] and seq[last + index:last + index + len(min_pattern)] == min_pattern:
            return len(seq[:last + index])
    return []


# returns the index of the nth even number
def find_even(seq, n):
    for index, e in enumerate(seq):
        if e % 2 == 0 and e != 0:
            n -= 1
            if n == 0:
                return index
    return []

start = time.time()
print(answer())
print("Took {} seconds".format(time.time() - start))
