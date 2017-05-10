package base.data;

import base.data.util.Vector;

public abstract class SceneObject {

    private Material material;
    private Vector position;

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

}
