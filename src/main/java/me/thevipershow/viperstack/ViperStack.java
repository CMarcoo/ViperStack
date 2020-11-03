package me.thevipershow.viperstack;

import me.thevipershow.viperstack.packages.classes.BukkitPackage;
import me.thevipershow.viperstack.packages.constructors.BukkitConstructor;
import me.thevipershow.viperstack.packages.fields.BukkitMethod;

public final class ViperStack extends AbstractViperStack {

    protected ViperStack(final Object material) {
        super(material);
    }

    public Object bukkitStack() {
        final Class<?> itemStackClass = BukkitPackage.PACKAGE.reflectInto(BukkitPackage.PackageType.ITEM_STACK);

        final Object stack;
        if (amount != 0x01) {
            stack = BukkitConstructor.ITEM_STACK.newInstance(super.material, super.amount);
        } else {
            stack = BukkitConstructor.ITEM_STACK.newInstance(super.material);
        }

        final boolean nullName = name == null, nullLore = lore == null;

        if (!nullName || !nullLore) {
            final Object meta = BukkitMethod.GET_ITEM_META.invokeMethod(stack);
            if (!nullLore) { BukkitMethod.SET_LORE.invokeMethod(meta, super.lore); }
            if (!nullName) { BukkitMethod.SET_DISPLAY_NAME.invokeMethod(meta, super.name); }
            BukkitMethod.SET_ITEM_META.invokeMethod(stack, meta);
        }

        return stack;
    }
}
