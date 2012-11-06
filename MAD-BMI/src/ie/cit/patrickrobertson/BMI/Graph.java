package ie.cit.patrickrobertson.BMI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class Graph extends View {

	ResultsManager rm;
	Paint paintGrey, paintWhite;
	int xSpacing, ySpacing, size, offsetX, offsetY;

	// setup details and work out ranges
	public Graph(Context context, ResultsManager rm, int size, int offsetX,
			int offsetY) {
		super(context);
		this.rm = rm;
		this.size = size;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		paintGrey = new Paint();
		paintWhite = new Paint();
		this.ySpacing = size / getMaxValue();
		this.xSpacing = size / (rm.getResults().size());
	}

	public void onDraw(Canvas canvas) {
		paintGrey.setColor(android.graphics.Color.LTGRAY);
		paintGrey.setStrokeWidth(5);
		paintWhite.setColor(android.graphics.Color.WHITE);
		int position = 15+offsetX;
		/*
		 * canvas.drawLine(xSpacing*1, 29*ySpacing,xSpacing*2, 26*ySpacing,
		 * paintGrey); canvas.drawLine(xSpacing*2, 26*ySpacing,xSpacing*3,
		 * 25*ySpacing, paintGrey); canvas.drawLine(xSpacing*3,
		 * 25*ySpacing,xSpacing*4, 15*ySpacing, paintGrey);
		 */

		for (Result result : rm.getResults()) {
			// check if its the last entry and don't draw line
			if (rm.getResults().indexOf(result) == (rm.getResults().size()) - 1) {
				// add last number to line
				int startYCoord = ((getMaxValue() - result.getBMI()) * ySpacing)
						+ offsetY;
				int startXCoord = position;
				canvas.drawText(String.valueOf(result.getBMI()), startXCoord,
						startYCoord - 5, paintWhite);
			} else {
				// get details of this result
				int startYCoord = ((getMaxValue() - result.getBMI()) * ySpacing)
						+ offsetY;
				int startXCoord = position;
				// get next result in line
				int endYCoord = ((getMaxValue() - rm.getResults()
						.get(rm.getResults().indexOf(result) + 1).getBMI()) * ySpacing)
						+ offsetY;
				int endXCoord = position + xSpacing;
				// drawline
				canvas.drawLine(startXCoord, startYCoord, endXCoord, endYCoord,
						paintGrey);
				// add value next to point except first entry, as it class with
				// labels
				if (rm.getResults().indexOf(result) != 0) {
					canvas.drawText(String.valueOf(result.getBMI()),
							startXCoord, startYCoord - 5, paintWhite);
				}
				// add value to take to next position
				position += xSpacing;
			}
		}
		// draw Labels
		canvas.drawLine(15 + offsetX, offsetY, 15 + offsetX, size + offsetY,
				paintWhite);
		canvas.drawLine(0 + offsetX, (size + offsetY) - 15, size + offsetX,
				(size - 15) + offsetY, paintWhite);
		for (int x = 0; x < getMaxValue(); x += 5) {
			canvas.drawText(String.valueOf((getMaxValue() - x)), offsetX,
					(x * ySpacing) + offsetY, paintWhite);
		}
		for (int x = 0; x < rm.getResults().size(); x++) {
			canvas.drawText(String.valueOf(x), (x * xSpacing) + offsetX, size
					+ offsetY, paintWhite);
		}

	}

	private int getMaxValue() {
		List<Integer> temp = new ArrayList<Integer>();
		for (Result x : rm.getResults()) {
			temp.add(x.getBMI());
		}
		Collections.sort(temp);
		return temp.get(rm.getResults().size() - 1);
	}

}