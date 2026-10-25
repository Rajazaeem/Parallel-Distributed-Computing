public class PacketSymmetryAuditor {
    public static void main(String[] args) {
        String payload = "racecar";
        int n = payload.length();

        TransactionStack stack = new TransactionStack(n);
        RequestQueue queue = new RequestQueue(n);

        for (int i = 0; i < n; i++) {
            char c = payload.charAt(i);
            stack.push(c);
            queue.enqueue(c);
        }

        boolean symmetric = true;

        while (!stack.isEmpty()) {
            char fromStack = stack.pop();
            char fromQueue = queue.dequeue();

            if (fromStack != fromQueue) {
                symmetric = false;
                break;
            }
        }

        if (symmetric) {
            System.out.println("\"" + payload + "\" is a palindrome.");
        } else {
            System.out.println("\"" + payload + "\" is not a palindrome.");
        }
    }
}
