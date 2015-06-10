/*
 * Copyright (c) 2015. Matthias Neugebauer. All rights reserved.
 */

package de.mtneug.maze_cli.cli.adapter.outputs;

import de.mtneug.maze_cli.annotations.OutputAdapter;
import de.mtneug.maze_cli.model.MazeSolutions;
import de.mtneug.maze_cli.outputs.AbstractMazeOutput;
import de.mtneug.maze_cli.outputs.SeminarSerializationOutput;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

/**
 * Adapter for the maze seminar serialization output.
 *
 * @author Matthias Neugebauer
 * @version 1.0
 * @since 1.0
 */
@OutputAdapter(name = "seminar")
public class SeminarSerializationAdapter extends AbstractFileOutputAdapter {
  /**
   * Code to instantiate a new maze seminar serialization output configured with the given parameters.
   *
   * @param mazeSolutions The maze and its solution(s) to output.
   * @param commandLine   Parsed CLI arguments.
   * @return The returned object of the maze seminar serialization output.
   * @throws ParseException
   */
  @Override
  public AbstractMazeOutput doGenerate(MazeSolutions mazeSolutions, CommandLine commandLine) throws ParseException {
    return new SeminarSerializationOutput(
        mazeSolutions,
        getPath(commandLine)
    );
  }
}
