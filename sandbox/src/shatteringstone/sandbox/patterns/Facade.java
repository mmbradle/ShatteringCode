package shatteringstone.sandbox.patterns;

/* Complex parts */

class CPU {
	public void freeze() { System.out.println("Freeze"); }
	public void jump(long position) { System.out.println("Jump"); }
	public void execute() { System.out.println("Execute"); }
}

class Memory {
	public void load(long position, byte[] data) { 
		System.out.println("Loading");
	}
}

class HardDrive {
	public byte[] read(long lba, int size) {
		System.out.println("Reading");
		return new byte[] {0x00, 0x00};
	}
}

/* Facade */

class Computer {
	private static final long BOOT_ADDRESS = 0;
	private static final long BOOT_SECTOR = 0;
	private static final int SECTOR_SIZE = 0;
	private CPU cpu;
	private Memory memory;
	private HardDrive hardDrive;

	public Computer() {
		this.cpu = new CPU();
		this.memory = new Memory();
		this.hardDrive = new HardDrive();
	}

	public void startComputer() {
		cpu.freeze();
		memory.load(BOOT_ADDRESS, hardDrive.read(BOOT_SECTOR, SECTOR_SIZE));
		cpu.jump(BOOT_ADDRESS);
		cpu.execute();
	}
}

/* Client */
public class Facade {
	public static void main(String[] args) {
		Computer facade = new Computer();
		facade.startComputer();
	}
}
