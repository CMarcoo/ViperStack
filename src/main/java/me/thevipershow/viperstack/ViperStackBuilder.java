package me.thevipershow.viperstack;

import java.util.Arrays;
import java.util.List;

public final class ViperStackBuilder extends AbstractViperStack {
    public ViperStackBuilder(final Object material) {
        super(material);
    }

    public final ViperStackBuilder amount(final int amount) {
        setAmount(amount);
        return this;
    }

    public final ViperStackBuilder material(final Object material) {
        if (isMaterial(material)) {
            super.material = material;
        } else {
            throw new IllegalArgumentException(String.format("Passed object was not a Material, was (%s).", material.getClass().getSimpleName()));
        }
        return this;
    }

    public final ViperStackBuilder name(final String name) {
        setName(name);
        return this;
    }

    public final ViperStackBuilder lore(final String... lore) {
        setLore(Arrays.asList(lore));
        return this;
    }

    public final ViperStackBuilder lore(final List<String> lore) {
        setLore(lore);
        return this;
    }

    public final ViperStack build() {
        final ViperStack viperStack = new ViperStack(this.material);
        viperStack.setName(name);
        viperStack.setAmount(amount);
        viperStack.setLore(lore);
        return viperStack;
    }
}
