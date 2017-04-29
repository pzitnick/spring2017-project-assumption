package base.data;

public interface Renderable {
    void transform();

    Material getMaterial();

    void setMaterial(Material material);
}
