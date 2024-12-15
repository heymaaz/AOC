package TwentyFour.Day9;

import java.util.ArrayList;
import java.util.List;

public class DiskFragmenter {
    public static void main(String[] args) {

        String input = Input.readFile("TwentyFour/Day9/input.txt");

        List<Integer> disk = getDisk(input);
        // System.out.println(disk);

        List<Integer> diskFragmented = defragDisk(disk);
        // System.out.println(diskFragmented);

        System.out.println("Method 1");
        System.out.println(getChecksum(diskFragmented));

        List<Integer> diskFragmented2 = defragDisk2(disk);
        // System.out.println(diskFragmented);

        System.out.println("Method 2");
        System.out.println(getChecksum(diskFragmented2));
        
    }

    static List<Integer> getDisk(String input) {
        List<Integer> disk = new ArrayList<>();
        int count = 0;
        for(int i = 0; i<input.length(); i++) {
            if(i%2==0) {
                // Add the disk elements
                int temp = Integer.parseInt(input.substring(i, i+1));
                for(int j = 0; j<temp; j++) {
                    disk.add(count);
                }
                count++;
            }
            else {
                // Add the free spaces ..
                int temp = Integer.parseInt(input.substring(i, i+1));
                for(int j = 0; j<temp; j++) {
                    disk.add(null);
                }
            }
        }
        return disk;
    }

    static List<Integer> defragDisk(List<Integer> disk) {
        List<Integer> tmpDisk = new ArrayList<>(disk);
        List<Integer> diskFragmented = new ArrayList<>();
        int lastIndex = tmpDisk.size()-1;
        for(int i = 0; i<tmpDisk.size(); i++) {
            if(tmpDisk.get(i) != null) {
                diskFragmented.add(tmpDisk.get(i));
            }
            else {
                while(tmpDisk.get(lastIndex) == null)
                    lastIndex-- ;
                if(lastIndex<diskFragmented.size())
                    break;
                diskFragmented.add(tmpDisk.get(lastIndex));
                tmpDisk.set(lastIndex, null);
            }
        }
        return diskFragmented;
    }

    static List<Integer> defragDisk2(List<Integer> disk) {
        List<Integer> diskFragmented = new ArrayList<>(disk);
        int from = diskFragmented.size()-1, to = diskFragmented.size()-1;
        while(to>0) {
            while(to>0 && diskFragmented.get(to)==null) {
                to--;
            }
            from = to;
            while(from>0 && diskFragmented.get(from) != null && diskFragmented.get(from).equals(diskFragmented.get(to))) {
                from--;
            }
            int size = to-from;
            int index = -1;
            for(int j = 0; j<diskFragmented.size() && index == -1; j++) {
                if(diskFragmented.get(j)!=null)
                    continue;
                int k = j;
                while(k<diskFragmented.size() && diskFragmented.get(k)==null)
                    k++;

                if(k-j >= size) {
                    index=j-1;
                    break;
                }
            }
            if (index != -1 && index < from) {
                for (int counter = 1; from + counter <= to; counter++) {
                    diskFragmented.set(index + counter, diskFragmented.get(from + counter));
                    diskFragmented.set(from + counter, null);
                }
            }
            to = from;

            // if(index!=-1 && index<from) {
            //     //swap
            //     int counter = 1;
            //     while(from+counter<=to) {
            //         diskFragmented.set(index+counter, diskFragmented.get(from+counter));
            //         diskFragmented.set(from+counter, null);

            //         counter++;
            //     }
            // }
            // to=from;
        }
        return diskFragmented;
    }

    static Long getChecksum(List<Integer> diskFragmented) {
        Long checksum = 0l;
        for(int i = 0; i<diskFragmented.size(); i++) {
            if(diskFragmented.get(i)==null)
                continue;
            checksum += i*diskFragmented.get(i);
        }
        return checksum;
    }
}


/*
 * 0 -> 1
 * 1 -> null
 * 2 -> 2
 * 3 -> null
 * 4 -> 3
 * 5 -> null
 * 6 -> 4
 * 7 -> null
 * 8 -> 5
 * less
 * 6384282079460
 * 6384282079460
 * more
 * 7300283014366
 * 15868383645408
 */