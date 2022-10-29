package exercise;

// BEGIN
public class ReversedSequence implements CharSequence{
    private final String charSequence;

    public ReversedSequence(String charSequence) {
        this.charSequence = new StringBuilder(charSequence).reverse().toString();
    }

    @Override
    public int length() {
        return this.charSequence.length();
    }

    @Override
    public char charAt(int i) {
        return this.charSequence.charAt(i);
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        return this.charSequence.subSequence(i, i1);
    }

    @Override
    public String toString() {
        return this.charSequence;
    }
}
// END
