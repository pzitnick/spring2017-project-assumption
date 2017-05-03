package base.data;

public abstract class SceneObject {
    private Material material;

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
