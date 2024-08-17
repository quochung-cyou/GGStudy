package web.common.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableUtils {
    public static final int DEFAULT_PAGE_NUMBER = 0;
    public static final int DEFAULT_PAGE_SIZE = 20;

    public static Pageable createPageable(int size, int page, String sortBy) {
        return PageRequest.of(Math.max(page, DEFAULT_PAGE_NUMBER), Math.min(Math.max(size, 1), DEFAULT_PAGE_SIZE), Sort.Direction.ASC, sortBy);
    }
}
