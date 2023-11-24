package com.skillbox;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Collections<T> {


    public static <T, R> List<R> map(Iterator<T> iterator, Function1<? super T, ? extends R> mapper) {
        List<R> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(mapper.apply(iterator.next()));
        }
        return list;
    }

    public static <T> List<T> filter(Iterator<T> iterator, Predicate<T> predicate) {
        return doIteration(iterator, (element) -> {
            if (!predicate.test(element)) {
                return null;
            }
            return element;
        });
    }

    public static <T> List<T> takeWhile(Iterator<T> iterator, Predicate<T> predicate) {
        List<T> list = new ArrayList<>();
        while ((iterator.hasNext())) {
            var element = iterator.next();
            if (!predicate.test(element)) {
                break;
            }
            list.add(element);
        }
        return list;


    }

    public static <T> List<T> takeUnless(Iterator<T> iterator, Predicate<T> predicate) {

        return takeWhile(iterator, predicate.not());
    }


    public static <T> T foldr(Iterator<T> iterator, int first, Function2<T, T, T> fold) {
        Iterator<T> reverseIterator = reverse(iterator);
        return foldl(reverseIterator, first, fold);
    }

    public static <T> T foldl(Iterator<T> iterator, int first, Function2<T, T, T> fold) {
        T result = null;
        int counter = 0;
        while ((iterator.hasNext())) {
            var element = iterator.next();
            counter++;
            if (counter > first) {
                if (result == null) {
                    result = element;
                    continue;
                }
                result = fold.apply(result, element);
            }
        }
        return result;
    }

    private static <T> List<T> doIteration(Iterator<T> iterator, Function1<T, T> ifDo) {
        List<T> result = new ArrayList<>();
        while (iterator.hasNext()) {
            var element = ifDo.apply(iterator.next());
            if (element != null) {
                result.add(element);
            }
        }
        return result;
    }


    private static <T> Iterator<T> reverse(Iterator<T> iterator) {
        List<T> toList = new ArrayList<>();
        List<T> reverseList = new ArrayList<>();
        while (iterator.hasNext()) {
            toList.add(iterator.next());
        }
        for (var i = toList.size() - 1; i >= 0; i--) {
            reverseList.add(toList.get(i));
        }
        return reverseList.iterator();

    }

}
