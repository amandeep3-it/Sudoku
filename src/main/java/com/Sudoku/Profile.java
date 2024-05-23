
package com.Sudoku;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.json.JSONObject;





public class Profile {

	public int SIZE = 9;
	public int SECTION_SIZE = (int) Math.sqrt(this.SIZE);
	private int NEW_GAME_PROB = 3;

	private Path profiles_dir = Paths.get("profiles");
	public boolean profiles_exist;

	public String name = null;
	public int steps = 0;
	public long current_timer = 0;
	public int[][] original_map = null;
	public int[][] map = null;



	public Profile() {
		this.profiles_exist = this.checkExists();
	}



	public void reset() {
		this.name = null;
		this.steps = 0;
		this.current_timer = 0;
		this.original_map = null;
		this.map = null;
	}



	public String[] getNames() {
		String[] files = new File(this.profiles_dir.toString()).list();
		for (int i = 0; i < files.length; i++) files[i] = files[i].replace(".json", "");
		return files;
	}

	public void get(String name) throws Exception {
		if (!this.validFileName(name)) throw new Exception("Invalid file name");
		if (
			!(new File(this.profiles_dir.toString(), name + ".json").exists())
		) throw new Exception("Profile does not exist");

		this.name = name;

		JSONObject content = new JSONObject(new String(Files.readAllBytes(
			Paths.get(this.profiles_dir.toString(), name + ".json")
		)));

		this.steps = content.getInt("steps");
		this.current_timer = content.getLong("current_timer");

		this.original_map = new int[this.SIZE][this.SIZE];
		this.map = new int[this.SIZE][this.SIZE];

		for (int r = 0; r < content.getJSONArray("original_map").length(); r++) {
			for (int c = 0; c < content.getJSONArray("original_map").getJSONArray(r).length(); c++)
				this.original_map[r][c] = content.getJSONArray("original_map").getJSONArray(r).getInt(c);
		}

		for (int r = 0; r < content.getJSONArray("map").length(); r++) {
			for (int c = 0; c < content.getJSONArray("map").getJSONArray(r).length(); c++)
				this.map[r][c] = content.getJSONArray("map").getJSONArray(r).getInt(c);
		}
	}

	public void create(String name) throws Exception {
		if (!this.validFileName(name)) throw new Exception("Invalid file name");

		this.name = name;
		this.steps = 0;
		this.current_timer = 0;

		this.original_map = this.generate();

		this.map = new int[this.SIZE][this.SIZE];

		for (int r = 0; r < this.original_map.length; r++) {
			Random rand = new Random();
			for (int i = 0; i < this.NEW_GAME_PROB; i++) {
				int ri = rand.nextInt(8 - 0 + 1) + 0;
				this.map[r][ri] = this.original_map[r][ri];
			}
		}

		this.save();
	}



	public void setValue(int row, int column, int value) throws Exception {
		this.map[row][column] = value;
		this.steps++;

		this.save();
	}

	public void updateTimer(long seconds) throws Exception {
		this.current_timer += seconds;

		this.save();
	}

	public void save() throws Exception {
		JSONObject content = new JSONObject();
		content.put("steps", this.steps);
		content.put("current_timer", this.current_timer);
		content.put("original_map", this.original_map);
		content.put("map", this.map);

		File f = new File(this.profiles_dir.toString());
		if (!f.exists()) f.mkdirs();

		FileWriter db = new FileWriter(
			Paths.get(this.profiles_dir.toString(), this.name + ".json").toString()
		);
		db.write(content.toString());
		db.close();
	}

	public void delete() {
		File f = new File(this.profiles_dir.toString(), this.name + ".json");
		if (f.exists()) f.delete();
	}



	private boolean checkExists() {
		File f = new File(this.profiles_dir.toString());
		return (f.exists() && (f.list().length > 0));
	}

	private boolean validFileName(String name) {
		try {
			Paths.get(name);
			return true;
		} catch (InvalidPathException e) {}
		return false;
	}

	public boolean validate() {
		for (int r = 0; r < this.original_map.length; r++)
			for (int c = 0; c < this.original_map[r].length; c++)
				if (this.original_map[r][c] != this.map[r][c]) return false;
		return true;
	}



	private int[][] generate() {
		int[][] map = {
			{ 8, 1, 2, 9, 4, 3, 6, 7, 5 },
			{ 7, 5, 3, 6, 8, 2, 4, 9, 1 },
			{ 6, 4, 9, 1, 7, 5, 2, 8, 3 },
			{ 1, 5, 4, 3, 6, 9, 2, 8, 7 },
			{ 2, 3, 7, 8, 4, 5, 1, 6, 9 },
			{ 8, 9, 6, 7, 2, 1, 5, 3, 4 },
			{ 5, 2, 1, 4, 3, 8, 7, 9, 6 },
			{ 9, 7, 4, 5, 2, 6, 3, 1, 8 },
			{ 3, 6, 8, 9, 1, 7, 4, 5, 2 }
		};

		int[] map_shuffle = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		Random rand = new Random();

		for (int i = 0; i < map_shuffle.length; i++) {
			int r = rand.nextInt(map_shuffle.length);
			int t = map_shuffle[r];
			map_shuffle[r] = map_shuffle[i];
			map_shuffle[i] = t;
		}

		for (int r = 0; r < map.length; r++)
			for (int i = 0; i < map[r].length; i++) map[r][i] = map_shuffle[map[r][i] - 1];

		return map;
	}
}
