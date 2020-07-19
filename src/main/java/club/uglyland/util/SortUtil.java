package club.uglyland.util;

import club.uglyland.pojo.PanVO;

import java.util.Comparator;

/**
 * @Author : ZGQ
 * @Date : 2020/7/11 20:17
 * @Version : 1.0
 */
public class SortUtil {

    public static Comparator<PanVO> getPanComparator(Integer sortType) {
        if (sortType == 1) {
            return (o1, o2) -> o1.getName().compareTo(o2.getName());
        } else if (sortType == 2) {
            return (o1, o2) -> o2.getName().compareTo(o1.getName());
        } else if (sortType == 3) {
            return (o1, o2) -> o1.getTime().compareTo(o2.getTime());
        } else if (sortType == 4) {
            return (o1, o2) -> o2.getTime().compareTo(o1.getTime());
        } else if (sortType == 5) {
            return (o1, o2) -> o1.getFileSizeB().compareTo(o2.getFileSizeB());
        } else if (sortType == 6) {
            return (o1, o2) -> o2.getFileSizeB().compareTo(o1.getFileSizeB());
        } else if (sortType == 7) {
            return (o1, o2) -> o1.getUsername().compareTo(o2.getUsername());
        } else if (sortType == 8) {
            return (o1, o2) -> o2.getUsername().compareTo(o1.getUsername());
        } else return (o1, o2) -> 0;
    }

}
