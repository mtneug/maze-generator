/*
 * Copyright (c) 2015. Matthias Neugebauer. All rights reserved.
 */

package de.mtneug.maze_cli.algorithms;

import de.mtneug.maze_cli.model.Maze;

import java.util.Random;
import java.util.concurrent.Callable;

import static de.mtneug.maze_cli.algorithms.AbstractMazeAlgorithm.State.*;

/**
 * This is the base class for all maze generation algorithms. To implement a maze generator, one has to implement the
 * {@link #running()} method. It is expected that it generates the maze and set the {@link #state} to
 * {@link State#FINISHED} after it is done. At this point one can assume, that a proper initial maze is generated by
 * {@link #prepareMaze()}.
 * <p/>
 * To use a maze algorithm one should not use the {@link #running()} method directly, but rather {@link #call()}. Maze
 * algorithms are thus {@link Callable<Maze>} and can be run in threads.
 *
 * @author Matthias Neugebauer
 * @version 1.0
 * @since 1.0
 */
public abstract class AbstractMazeAlgorithm implements Callable<Maze> {
  /**
   * The random number generator object to use when creating the maze.
   */
  protected final Random random;

  /**
   * The produced maze.
   */
  protected Maze maze;

  /**
   * The current state the algorithm is in.
   */
  protected State state = NOT_STARTED;

  /**
   * Common constructor of a maze algorithm class.
   *
   * @param width  The width of the maze to generate.
   * @param height The height of the maze to generate.
   * @param random The random number generator object to use when creating the maze.
   */
  public AbstractMazeAlgorithm(int width, int height, Random random) {
    this.random = random;
    this.maze = new Maze(width, height);
    prepareMaze();
  }

  /**
   * Code that prepares an initial maze ready for use at {@link #running()}.
   */
  protected void prepareMaze() {
  }

  /**
   * Start the creation of the maze.
   *
   * @return The generated maze.
   * @throws Exception
   */
  @Override
  public Maze call() throws Exception {
    if (state != FINISHED) {
      state = RUNNING;
      running();
    }

    return maze;
  }

  /**
   * Code of the maze generation algorithm. It should also set the {@link #state} to {@link State#FINISHED}.
   */
  protected abstract void running();

  /**
   * Returns the generated maze.
   *
   * @return The generated maze.
   */
  public Maze getMaze() {
    return maze;
  }

  /**
   * Returns the state the algorithm is currently in.
   *
   * @return The current state.
   */
  public State getState() {
    return state;
  }

  /**
   * Enum describing the states a maze generation algorithm can be in.
   *
   * @author Matthias Neugebauer
   * @version 1.0
   * @since 1.0
   */
  public enum State {
    NOT_STARTED, RUNNING, FINISHED
  }
}
