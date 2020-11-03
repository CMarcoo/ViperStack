package me.thevipershow.viperstack;

public enum Primitives {
    INTEGER("int", Integer.class),
    DOUBLE("double", Double.class),
    FLOAT("float", Float.class),
    CHAR("char", Character.class),
    BYTE("byte", Byte.class),
    SHORT("short", Short.class),
    LONG("long", Long.class);

    private final String name;
    private final Class<? extends Comparable<?>> boxedClass;

    Primitives(final String name, Class<? extends Comparable<?>> boxedClass) {
        this.name = name;
        this.boxedClass = boxedClass;
    }

    public final String getName() {
        return name;
    }

    public final Class<? extends Comparable<?>> getBoxedClass() {
        return boxedClass;
    }

    public static boolean isAssignable(Class<?> first, Class<?> second) {
        final Primitives primitives = isPrimitive(first);
        if (primitives == null) {
            return false;
        } else {
            return primitives.getBoxedClass() == second;
        }
    }

    public static Primitives isPrimitive(final Class<?> c) {
        for (final Primitives value : values()) {
            if (c.getName().equals(value.getName())) {
                return value;
            }
        }
        return null;
    }
}
