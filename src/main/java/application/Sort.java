package application;
import org.json.simple.JSONObject;
public class Sort implements ISort{

	  public  DList iterativeQsort(DList emails) {
	        MyStack stack = new MyStack();
	        stack.push(0);
	        stack.push(emails.size());

	        while (!stack.isEmpty()) {
	            int end = (int)stack.pop();
	            int start = (int)stack.pop();
	            if (end - start < 2) {
	                continue;
	            }
	            int p = start + ((end - start) / 2);
	            p = partition(emails, p, start, end);

	            stack.push(p + 1);
	            stack.push(end);

	            stack.push(start);
	            stack.push(p);

	        }
			return emails;
	    }

	    /*
	     * Utility method to partition the array into smaller array, and
	     * comparing numbers to rearrange them as per quicksort algorithm.
	     */
	    private static int partition(DList input, int position, int start, int end) {
	        int l = start;
	        int h = end - 2;
	        int piv = Integer.parseInt((String) ((JSONObject) input.get(position)).get("priority"));
	        swap(input, position, end - 1);

	        while (l < h) {
	            if (Integer.parseInt((String) ((JSONObject) input.get(l)).get("priority")) < piv) {
	                l++;
	            } else if (Integer.parseInt((String) ((JSONObject) input.get(h)).get("priority")) >= piv) {
	                h--;
	            } else {
	                swap(input, l, h);
	            }
	        }
	        int idx = h;
	        if (Integer.parseInt((String) ((JSONObject) input.get(h)).get("priority")) < piv) {
	            idx++;
	        }
	        swap(input, end - 1, idx);
	        return idx;
	    }

	    /**
	     * Utility method to swap two numbers 
	     *
	     * @param d - DList on which swap will happen
	     * @param i
	     * @param j
	     */
	    private static void swap(DList d, int i, int j) {
	    	JSONObject temp = (JSONObject)d.get(i);
	        d.set(i, d.get(j));
	        d.set(j, temp);
	    }
}



