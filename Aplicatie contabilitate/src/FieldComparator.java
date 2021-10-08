import java.util.Comparator;

public class FieldComparator implements Comparator<Field>{

	@Override
	public int compare(Field a, Field b) {
		return a.getOrder() - b.getOrder();
	}

}
