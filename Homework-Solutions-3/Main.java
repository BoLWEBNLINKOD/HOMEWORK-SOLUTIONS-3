public class Main {
    public static void main(String[] args) {
        System.out.println("=== Running MUDBuilderDemo ===");
        MUDBuilderDemo.main(args);
        
        System.out.println("\n=== Running MUDPrototypeDemo ===");
        MUDPrototypeDemo.main(args);
        
        System.out.println("\n=== Running MUDCombinedDemo ===");
        MUDCombinedDemo.main(args);
    }
}

public interface IDungeonBuilder {
    IDungeonBuilder setDungeonName(String name);
    IDungeonBuilder addRoom(Room room);
    IDungeonBuilder addNPC(NPC npc);
    Dungeon build();
}

import java.util.ArrayList;
import java.util.List;

public class SimpleDungeonBuilder implements IDungeonBuilder {
    private String name;
    private List<Room> rooms = new ArrayList<>();
    private List<NPC> npcs = new ArrayList<>();

    @Override
    public IDungeonBuilder setDungeonName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public IDungeonBuilder addRoom(Room room) {
        rooms.add(room);
        return this;
    }

    @Override
    public IDungeonBuilder addNPC(NPC npc) {
        npcs.add(npc);
        return this;
    }

    @Override
    public Dungeon build() {
        return new Dungeon(name, rooms, npcs);
    }
}

import java.util.List;

public class Dungeon {
    private String name;
    private List<Room> rooms;
    private List<NPC> npcs;

    public Dungeon(String name, List<Room> rooms, List<NPC> npcs) {
        this.name = name;
        this.rooms = rooms;
        this.npcs = npcs;
    }

    public void displayInfo() {
        System.out.println("Dungeon: " + name);
        System.out.println("Rooms: " + rooms.size());
        System.out.println("NPCs: " + npcs.size());
    }
}

public interface CloneableGameEntity {
    CloneableGameEntity cloneEntity();
}

public class Room implements CloneableGameEntity {
    private String name;
    private String description;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public Room cloneEntity() {
        return new Room(this.name, this.description);
    }
}

public class NPC implements CloneableGameEntity {
    private String name;
    private String description;
    private int health;

    public NPC(String name, String description, int health) {
        this.name = name;
        this.description = description;
        this.health = health;
    }

    @Override
    public NPC cloneEntity() {
        return new NPC(this.name, this.description, this.health);
    }
}

public class MUDBuilderDemo {
    public static void main(String[] args) {
        Dungeon dungeon = new SimpleDungeonBuilder()
                .setDungeonName("Dark Cavern")
                .addRoom(new Room("Entrance", "A dark and gloomy entrance"))
                .addNPC(new NPC("Goblin", "A small but vicious creature", 50))
                .build();
        dungeon.displayInfo();
    }
}

public class MUDPrototypeDemo {
    public static void main(String[] args) {
        Room prototypeRoom = new Room("Hallway", "A long dark hallway");
        Room clonedRoom = prototypeRoom.cloneEntity();

        System.out.println("Prototype Room: " + prototypeRoom);
        System.out.println("Cloned Room: " + clonedRoom);
    }
}

public class MUDCombinedDemo {
    public static void main(String[] args) {
        Dungeon dungeon = new SimpleDungeonBuilder()
                .setDungeonName("Ancient Ruins")
                .addRoom(new Room("Main Hall", "A vast hall with high ceilings"))
                .build();

        Room templateRoom = new Room("Side Chamber", "A small chamber with relics");
        Room clonedRoom1 = templateRoom.cloneEntity();
        Room clonedRoom2 = templateRoom.cloneEntity();

        System.out.println("Dungeon created with a template room.");
        System.out.println("Cloned Room 1: " + clonedRoom1);
        System.out.println("Cloned Room 2: " + clonedRoom2);
    }
}
