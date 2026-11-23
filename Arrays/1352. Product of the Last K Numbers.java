class ProductOfNumbers {
    ArrayList<Integer> p;

    public ProductOfNumbers() {
        p = new ArrayList<>();
        p.add(1);
    }

    public void add(int num) {
        if(num == 0) {
            p.clear();
            p.add(1);
        } else {
            p.add(p.get(p.size() - 1) * num);
        }
    }

    public int getProduct(int k) {
        if(k >= p.size()) return 0;
        return p.get(p.size() - 1) / p.get(p.size() - k - 1);
    }
}
