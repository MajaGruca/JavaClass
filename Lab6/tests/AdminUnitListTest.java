import java.util.Locale;

public class AdminUnitListTest {
    @org.junit.Test
    public void getNeighbors() throws Exception {
        AdminUnitList ad = new AdminUnitList();
        ad.read("admin-units.csv");

        double t1 = System.nanoTime()/1e6;
        AdminUnitList neighbours = ad.getNeighbors(ad.units.get(4),15);
        double t2 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"t2-t1=%f\n",t2-t1);
        System.out.println("Test 1:\nSąsiedzi miejscowości Będkowice:\n");
        neighbours.list(System.out);

        t1 = System.nanoTime()/1e6;
        neighbours = ad.getNeighbors(ad.units.get(4453),15);
        t2 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"t2-t1=%f\n",t2-t1);
        System.out.println("Test 2:\nSasiedzi wojewodztwa małopolskiego: \n");
        neighbours.list(System.out);
    }

}