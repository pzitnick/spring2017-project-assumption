package base.data.util;

public class Vector3Modifier implements VectorModifier {

    private static VectorModifier modifier = new Vector3Modifier();

    private Vector3Modifier() {
    }

    public static VectorModifier getInstance() {
        return modifier;
    }

}
