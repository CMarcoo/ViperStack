package me.thevipershow.viperstack;

import java.util.Collections;
import java.util.List;
import me.thevipershow.viperstack.packages.classes.BukkitPackage;

public abstract class AbstractViperStack {

    protected Object material;
    protected int amount = 1;
    protected String name = null;
    protected List<String> lore = null;

    protected static boolean isMaterial(final Object o) {
        return o.getClass().isAssignableFrom(BukkitPackage.PACKAGE.reflectInto(BukkitPackage.PackageType.MATERIAL));
    }

    public AbstractViperStack(final Object material) {
        final Class<?> passedObjectClass = material.getClass();
        if (isMaterial(material)) {
            this.material = material;
        } else {
            throw new IllegalArgumentException(String.format("Passed object was not a Material, was (%s).", passedObjectClass.getSimpleName()));
        }
    }

    public final List<String> getLore() {
        return lore;
    }

    public final int getAmount() {
        return amount;
    }

    public final String getName() {
        return name;
    }

    public void setAmount(final int amount) {
        this.amount = amount;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setLore(final List<String> lore) {
        this.lore = lore;
    }
}
