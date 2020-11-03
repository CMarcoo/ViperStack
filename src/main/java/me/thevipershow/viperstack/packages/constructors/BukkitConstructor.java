package me.thevipershow.viperstack.packages.constructors;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import me.thevipershow.viperstack.Primitives;
import me.thevipershow.viperstack.packages.classes.BukkitPackage;

public enum BukkitConstructor {

    ITEM_STACK(BukkitPackage.PACKAGE.reflectInto(BukkitPackage.PackageType.ITEM_STACK).getConstructors());

    private final Constructor<?>[] constructors;

    BukkitConstructor(final Constructor<?>... constructors) {
        this.constructors = constructors;
    }

    public Constructor<?>[] getConstructors() {
        return constructors;
    }

    public final Constructor<?> findAdequateConstructor(final Object... objects) {

        for (final Constructor<?> constructor : this.constructors) {

            final Class<?>[] parameters = constructor.getParameterTypes();

            if (parameters.length == objects.length) {
                boolean valid = true;
                for (int i = 0; i < parameters.length; i++) {
                    final Class<?> oClass = objects[i].getClass();
                    final Class<?> pClass = parameters[i];
                    //System.out.println(pClass.getName() + " - " + oClass.getName());
                    if (!pClass.isAssignableFrom(oClass) && !Primitives.isAssignable(pClass, oClass)) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    return constructor;
                }
            }
        }
        return null;
    }

    public final Object newInstance(final Object... objects) {
        final Constructor<?> adequateConstructor = findAdequateConstructor(objects);
        if (adequateConstructor != null) {
            try {
                return adequateConstructor.newInstance(objects);
            } catch (final IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            throw new IllegalArgumentException("No constructor was found matching the objects order.");
        }
    }
}
