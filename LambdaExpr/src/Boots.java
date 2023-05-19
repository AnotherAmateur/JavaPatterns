
public class Boots {
    private static long globalId = 0;
    private long shoeId;
    private SoleTypes soleType;
    private ShoeUpper shoeUpper;
    private Author authorInfo;

    public long getShoeId() {
        return shoeId;
    }

    public void setShoeId(long shoeId) {
        this.shoeId = shoeId;
    }

    public SoleTypes getSoleType() {
        return soleType;
    }

    public void setSoleType(SoleTypes soleType) {
        this.soleType = soleType;
    }

    public ShoeUpper getShoeUpper() {
        return shoeUpper;
    }

    public void setShoeUpper(ShoeUpper shoeUpper) {
        this.shoeUpper = shoeUpper;
    }

    public String GetAuthorString() {
        return authorInfo.toString();
    }

    public Author getAuthorInfo() {
        return authorInfo;
    }

    public void setAuthorInfo(Author authorInfo) {
        this.authorInfo = authorInfo;
    }

    public Boots(){}

    public Boots(Author author, ShoeUpper shoeUpper, SoleTypes soleType) {
        this.shoeId = globalId++;
        this.authorInfo = author;
        this.shoeUpper = shoeUpper;
        this.soleType = soleType;
    }

    @Override
    public String toString() {
        return String.join(". ", "Boots id:" + Long.toString(shoeId), String.join(" ", shoeUpper.toString(), soleType.toString(), authorInfo.toString()));
    }
}