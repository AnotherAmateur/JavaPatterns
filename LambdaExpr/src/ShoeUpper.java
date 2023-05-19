class ShoeUpper {
    private ShoeUpperMat shoeUpperMat;

    public void setShoeUpperMat(ShoeUpperMat shoeUpperMat) {
        this.shoeUpperMat = shoeUpperMat;
    }

    public ShoeUpper(){}

    public ShoeUpper(ShoeUpperMat shoeUpperMat) {
        this.shoeUpperMat = shoeUpperMat;
    }

    @Override
    public String toString() {
        return shoeUpperMat.toString();
    }
}