package me.thevipershow.viperstack.packages.fields;

import java.lang.reflect.Method;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import me.thevipershow.viperstack.packages.classes.BukkitPackage;

public enum BukkitMethod {
    GET_ITEM_META(BukkitPackage.PackageType.ITEM_STACK,
            "getItemMeta",
            new Class<?>[]{},
            BukkitPackage.PACKAGE.reflectInto(BukkitPackage.PackageType.ITEM_META)),

    SET_ITEM_META(BukkitPackage.PackageType.ITEM_STACK,
            "setItemMeta",
            new Class<?>[]{BukkitPackage.PACKAGE.reflectInto(BukkitPackage.PackageType.ITEM_META)},
            null),

    SET_DISPLAY_NAME(BukkitPackage.PackageType.ITEM_META,
            "setDisplayName",
            new Class<?>[]{String.class},
            null),

    SET_LORE(BukkitPackage.PackageType.ITEM_META,
            "setLore",
            new Class<?>[]{List.class},
            null);

    private final BukkitPackage.PackageType packageType;
    private final String methodName;
    private final Class<?>[] parametersOrder;
    private final Class<?> returnType;

    private static final Map<BukkitMethod, Method> cachedMethod = new EnumMap<>(BukkitMethod.class);

    BukkitMethod(final BukkitPackage.PackageType methodOwner, final String methodName, final Class<?>[] parametersOrder, final Class<?> returnType) {
        this.packageType = methodOwner;
        this.methodName = methodName;
        this.parametersOrder = parametersOrder;
        this.returnType = returnType;
    }

    public final Method getMethod() {
        if (cachedMethod.containsKey(this)) {
            return cachedMethod.get(this);
        } else {
            final Class<?> clazz = BukkitPackage.PACKAGE.reflectInto(this.packageType);
            try {
                final Method obtained = clazz.getMethod(this.methodName, this.parametersOrder);
                cachedMethod.put(this, obtained);
                return obtained;
            } catch (final NoSuchMethodException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public final Object invokeMethod(final Object invokeFrom, final Object... objects) {
        final Method m = getMethod();
        if (m == null) {
            throw new IllegalArgumentException("Your Minecraft server software is missing a proper " + methodName + " implementation.");
        } else {
            try {
                if (BukkitPackage.PACKAGE.reflectInto(this.packageType).isAssignableFrom(invokeFrom.getClass())) {
                    if (objects.length != this.parametersOrder.length) {
                        throw new IllegalArgumentException("The provided arguments number was not " + this.parametersOrder.length);
                    }
                    if (objects.length == 0) {
                        return m.invoke(invokeFrom);
                    } else {
                        for (int i = 0; i < objects.length; i++) {
                            final Class<?> oClass = objects[i].getClass();
                            final Class<?> pClass = this.parametersOrder[i];
                            if (!pClass.isAssignableFrom(oClass)) {
                                throw new IllegalArgumentException(String.format(
                                        "Argument at position %d should have been of type %s, but is %s.",
                                        i,
                                        pClass.getSimpleName(),
                                        oClass.getSimpleName()));
                            }
                        }
                        return m.invoke(invokeFrom, objects);
                    }
                } else {
                    throw new IllegalArgumentException("method " + this.methodName + " cannot be invoked from " + invokeFrom.getClass().getSimpleName());
                }
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public final BukkitPackage.PackageType getPackageType() {
        return packageType;
    }

    public final String getMethodName() {
        return methodName;
    }

    public final Class<?>[] getParametersOrder() {
        return parametersOrder;
    }

    public final Class<?> getReturnType() {
        return returnType;
    }
}
