package maze_generator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GeoMazeGen {
	
	static final float lat = 38.875189f;
	static final float lng = -94.784753f;
	static final float wall_dist = .00005f; //5.5m Each path is double this
	static final char[][] maze = { {'+','-','-','+','-','-','+','-','-','+','-','-','+','-','-','+','-','-','+','-','-','+','-','-','+','-','-','+','-','-','+'},
					{'|',' ',' ','|',' ',' ',' ',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ','|'},
					{'+',' ',' ','+',' ',' ','+','-','-','+',' ',' ','+',' ',' ','+',' ',' ','+','-','-','+',' ',' ','+',' ',' ','+','-','-','+'},
					{'|',' ',' ',' ',' ',' ','|',' ',' ','|',' ',' ','|',' ',' ','|',' ',' ','|',' ',' ','|',' ',' ','|',' ',' ',' ',' ',' ','|'},
					{'+','-','-','+','-','-','+',' ',' ','+',' ',' ','+','-','-','+',' ',' ','+',' ',' ','+',' ',' ','+','-','-','+',' ',' ','+'},
					{'|',' ',' ',' ',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ',' ',' ',' ','|',' ',' ','|',' ',' ',' ',' ',' ','|',' ',' ','|'},
					{'+',' ',' ','+',' ',' ','+','-','-','+','-','-','+','-','-','+','-','-','+',' ',' ','+','-','-','+',' ',' ','+',' ',' ','+'},
					{'|',' ',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ',' ',' ',' ','|',' ',' ','|',' ',' ','|'},
					{'+',' ',' ','+','-','-','+','-','-','+',' ',' ','+','-','-','+',' ',' ','+','-','-','+',' ',' ','+',' ',' ','+',' ',' ','+'},
					{'|',' ',' ',' ',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','|',' ',' ','|',' ',' ',' ',' ',' ','|'},
					{'+','-','-','+','-','-','+',' ',' ','+','-','-','+','-','-','+','-','-','+','-','-','+',' ',' ','+','-','-','+',' ',' ','+'},
					{'|',' ',' ','|',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ','|'},
					{'+',' ',' ','+',' ',' ','+','-','-','+',' ',' ','+','-','-','+',' ',' ','+','-','-','+',' ',' ','+',' ',' ','+','-','-','+'},
					{'|',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ','|'},
					{'+',' ',' ','+','-','-','+','-','-','+',' ',' ','+',' ',' ','+','-','-','+','-','-','+',' ',' ','+','-','-','+',' ',' ','+'},
					{'|',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ','|',' ',' ','|'},
					{'+','-','-','+',' ',' ','+',' ',' ','+',' ',' ','+','-','-','+','-','-','+',' ',' ','+','-','-','+','-','-','+',' ',' ','+'},
					{'|',' ',' ',' ',' ',' ','|',' ',' ','|',' ',' ',' ',' ',' ','|',' ',' ','|',' ',' ','|',' ',' ',' ',' ',' ','|',' ',' ','|'},
					{'+',' ',' ','+','-','-','+',' ',' ','+','-','-','+',' ',' ','+',' ',' ','+',' ',' ','+',' ',' ','+',' ',' ','+',' ',' ','+'},
					{'|',' ',' ',' ',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ','|',' ',' ',' ',' ',' ','|'},
					{'+','-','-','+','-','-','+','-','-','+','-','-','+','-','-','+','-','-','+','-','-','+','-','-','+','-','-','+','-','-','+'} };
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LatLong[][] maze_arr = new LatLong[maze.length][maze[0].length];
		ArrayList<Wall> walls = new ArrayList<Wall>();
		
		int center_x = maze.length % 2 == 0 ? (maze.length / 2) : (maze.length / 2) + 1;
		int center_y = maze[0].length % 2 == 0 ? (maze[0].length / 2) : (maze[0].length / 2) + 1;
		
		for(int i = 0; i < maze.length; i++)
		{
			for(int j = 0; j < maze[i].length; j++)
			{
				LatLong ll = new LatLong();
				if(maze[i][j] == '+') 
				{
					int offset_lat = center_x - i;
					int offset_lng = center_y - j;
					float t_lat = lat + (wall_dist * -1 * offset_lat);
					float t_lng = lng + (wall_dist * -1 * offset_lng);
					ll.lat = t_lat;
					ll.lng = t_lng;
					maze_arr[i][j] = ll;
				}
			}		
		}
		for(int i = 0; i < maze.length; i++)
		{
			for(int j = 0; j < maze[i].length; j++)
			{
				if(maze[i][j] == '+') {
					if(maze.length > i+2 && maze[i+1][j] == '|')
					{
						//N S Wall
						walls.add(new Wall(maze_arr[i][j], maze_arr[i+2][j], 0));
						System.out.println(walls.get(walls.size()-1).toString());
					}
					if(maze[i].length > j+3 && maze[i][j+1] == '-')
					{
						//E W Wall
						walls.add(new Wall(maze_arr[i][j], maze_arr[i][j+3], 1));
						System.out.println(walls.get(walls.size()-1).toString());
					}
				}
			}
		}
	}
}
class LatLong {
	float lat;
	float lng;
	
	public String toString() {
		return lng + "," + lat;
	}
}
class Wall {
	LatLong s;
	LatLong e;
	int dir; //0 == N or S //1 == E or W 
	public Wall(LatLong s, LatLong e, int dir) {
		this.s = s;
		this.e = e;
		this.dir = dir;
	}
	public String toString() {
		String a = "<Placemark><styleUrl>#yellowLineGreenPoly</styleUrl><LineString><extrude>1</extrude><tessellate>1</tessellate><altitudeMode>absolute</altitudeMode><coordinates>\n";
        String b = "</coordinates></LineString></Placemark>";
		return a + s.toString() +",0 " +e.toString() + ",0 \n" + b;
		
	}	
}