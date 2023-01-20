![Mon logo Github](https://avatars.githubusercontent.com/u/8670635?s=100&u=70a5c310270795abf38caaa723dd9842dd28f02a&v=4)

<span style="color:orange">Olivier Mathé 08/09/2022</span>
> Sealed Classes (JEP 409)

# Rôle d'une sealed class

Cette fonctionalité permet un contrôle de l'héritage en Java.

Le scellage permet à une classe ou une interface de définir les sous-types autorisés.

En d'autres termes une classe ou une interface peut maintenant définir quelles classes peut l'implémenter ou l'étendre.
C'est une fonctionalité utile pour la modélisation du domaine et elle accroît la sécurité.

## Modélisation du domaine métier
Imaginons un domaine métier qui gère des voitures, des camions mais pas de motos. Lors de la création
de la classe abstraite *Vehicule* on devrait être capable de n'autoriser que les classes *Car* et *Truck*
à étendre cette classe. De cette manière on s'assure que la classe abstraite *Vehicule* est corretement exploitée.

Dans les versions précédentes Java fournissait certaines options pour contrôler l'héritage mais de manière
limitée.

## L'approche package-private
Une classe finale ne peut avoir de sous-classes.
Une classe package-private ne peut avoir de sous-classes que dans le même package. Dit autrement, un utilisateur
ne peut accéder à une classe ou l'étendre que dans le même package.

## super classe accessible, pas extensible
L'objectif d'une classe scellée est d'autoriser l'accès mais de limiter son extensibilité.


# Création
La fonctionalité **sealed** introduit de nouveaux modificateurs et clauses:
- sealed
- non-sealed
- permits (clause)

## Interfaces scellées
Pour sceller une interface on peut appliquer le modificateur **sealed** à sa déclaration. la clause **permits**
spécifie les classes autorisées à implémenter l'interface.

```
public sealed interface Service permits Car, Truck {

    int getMaxServiceIntervalInMonths();

    default int getMaxDistanceBetweenServicesInKilometers() {
        return 100000;
    }
}
```
## Classes scellées

Comme pour les interfaces on peut sceller une classe en appliquant le modificateur **sealed**.
La clause **permits** devrait être définie après les clauses *extends* et *implements*
```
public abstract sealed class Vehicle permits Car, Truck {

    protected final String registrationNumber;

    public Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

}
```
Une classe autorisée doit définir un modificateur. Elle peut être déclarée *final* pour empêcher son extension.
```
public final class Truck extends Vehicle implements Service {

    private final int loadCapacity;

    public Truck(int loadCapacity, String registrationNumber) {
        super(registrationNumber);
        this.loadCapacity = loadCapacity;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }

    @Override
    public int getMaxServiceIntervalInMonths() {
        return 18;
    }
}
```
Une sous-classe autorisée peut aussi être déclarée comme scellée. Cependant si on la déclare
non scellée alors elle peut être étendue.
```
public non-sealed class Car extends Vehicle implements Service {

    private final int numberOfSeats;

    public Car(int numberOfSeats, String registrationNumber) {
        super(registrationNumber);
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    @Override
    public int getMaxServiceIntervalInMonths() {
        return 12;
    }
}
```

## Contraintes
Une classe scellée impose trois contraintes importantes à ces sous-classes autorisées:

- Toutes les sous-classes doivent appartenir au même module
- Chaque sous-classe doit explicitement étendre la classe scellée
- Chaque sous-classe doit définir un modificateur : *final*, *sealed*, or *non-sealed*


# Utilisation
## La manière classique
Quand on scelle une classe, on indique au code client de manière claire toutes les sous-classes autorisées.

La manière classique de travailler au niveau des sous-classes est d'utiliser un ensemble d'instructions *if-else*
```
if (vehicle instanceof Car) {
    return ((Car) vehicle).getNumberOfSeats();
} else if (vehicle instanceof Truck) {
    return ((Truck) vehicle).getLoadCapacity();
} else {
    throw new RuntimeException("Unknown instance of Vehicle");
}
```
## Pattern matching
En utilisant le pattern matching on évite le recours au cast
```
if (vehicle instanceof Car car) {
    return car.getNumberOfSeats();
} else if (vehicle instanceof Truck truck) {
    return truck.getLoadCapacity();
} else {
    throw new RuntimeException("Unknown instance of Vehicle");
}
```
Utiliser if-else complique la tâche du compilateur pour déterminer que l'on couvre toutes les sous-classes autorisées.
C'est la raison pour laquelle on lance une RuntimeException.

Dans une future version de Java le code client sera capable d'utiliser un *switch* à la place d'un *if-else* (JEP 375).
En utilisant le pattern matching du switch, le compilateur sera capable de vérifier que chaque sous-classe autorisée est couverte.
Donc il n'y aura plus besoin de l'option default dans le switch.

# Compatibilité
Examinons la compatibilité des classes scellées avec les autres focntionalités du langage Java comme *record*
et l'API de réflection.

## record
Les classes scellées travaillent très bien avec les records. Comme un *record* est implicitement final la hiérarchie scellée
est encore plus concise.
```
public sealed interface Vehicle permits Car, Truck {

    String getRegistrationNumber();
}
```
```
public record Car(int numberOfSeats, String registrationNumber) implements Vehicle {

    @Override
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }
}
```
```
public record Truck(int loadCapacity, String registrationNumber) implements Vehicle {

    @Override
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }
}
```
## Réflection
Les classes scellées sont également prises en charge par l'API de réflection grâce à deux méthodes ajoutées:

- La méthode *isSealed* renvoie vrai si l'interface ou la classe est scellée
- La méthode *getPermittedSubclasses* renvoie un tableau d'objets représentant toutes les sous-classes autorisées
```
Assertions.assertThat(truck.getClass().isSealed()).isEqualTo(false);
Assertions.assertThat(truck.getClass().getSuperclass().isSealed()).isEqualTo(true);
Assertions.assertThat(truck.getClass().getSuperclass().getPermittedSubclasses())
.contains(ClassDesc.of(truck.getClass().getCanonicalName()));
```