# multiAgents.py
# --------------
# Licensing Information:  You are free to use or extend these projects for 
# educational purposes provided that (1) you do not distribute or publish 
# solutions, (2) you retain this notice, and (3) you provide clear 
# attribution to UC Berkeley, including a link to 
# http://inst.eecs.berkeley.edu/~cs188/pacman/pacman.html
# 
# Attribution Information: The Pacman AI projects were developed at UC Berkeley.
# The core projects and autograders were primarily created by John DeNero 
# (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# Student side autograding was added by Brad Miller, Nick Hay, and 
# Pieter Abbeel (pabbeel@cs.berkeley.edu).


from util import manhattanDistance
from game import Directions
import random, util

from game import Agent

class ReflexAgent(Agent):
    """
      A reflex agent chooses an action at each choice point by examining
      its alternatives via a state evaluation function.

      The code below is provided as a guide.  You are welcome to change
      it in any way you see fit, so long as you don't touch our method
      headers.
    """


    def getAction(self, gameState):
        """
        You do not need to change this method, but you're welcome to.

        getAction chooses among the best options according to the evaluation function.

        Just like in the previous project, getAction takes a GameState and returns
        some Directions.X for some X in the set {North, South, West, East, Stop}
        """
        # Collect legal moves and successor states
        legalMoves = gameState.getLegalActions()

        # Choose one of the best actions
        scores = [self.evaluationFunction(gameState, action) for action in legalMoves]
        bestScore = max(scores)
        bestIndices = [index for index in range(len(scores)) if scores[index] == bestScore]
        chosenIndex = random.choice(bestIndices) # Pick randomly among the best

        "Add more of your code here if you want to"

        return legalMoves[chosenIndex]

    def evaluationFunction(self, currentGameState, action):
        """
        Design a better evaluation function here.

        The evaluation function takes in the current and proposed successor
        GameStates (pacman.py) and returns a number, where higher numbers are better.

        The code below extracts some useful information from the state, like the
        remaining food (newFood) and Pacman position after moving (newPos).
        newScaredTimes holds the number of moves that each ghost will remain
        scared because of Pacman having eaten a power pellet.

        Print out these variables to see what you're getting, then combine them
        to create a masterful evaluation function.
        """
        # Useful information you can extract from a GameState (pacman.py)
        successorGameState = currentGameState.generatePacmanSuccessor(action)
        newPos = successorGameState.getPacmanPosition()
        newFood = successorGameState.getFood()
        newGhostStates = successorGameState.getGhostStates()
        newScaredTimes = [ghostState.scaredTimer for ghostState in newGhostStates]

        def closestFood(currentPos, foodPos):
            foodDistance = []
            for food in foodPos :
                foodDistance.append(util.manhattanDistance(food, currentPos))
            return min(foodDistance) if len(foodDistance) > 0 else 1
        def ghostProximity(ghostState, currentPos, distanceMin, currentScore):
            for ghost in ghostState :
                if util.manhattanDistance(ghost.getPosition(), currentPos) <= distanceMin :
                    currentScore = currentScore - 30
            return currentScore
        def sumFoodDistance(currentPos, foodPos):
            foodDistance = []
            for food in foodPos :
                foodDistance.append(util.manhattanDistance(food, currentPos))
            return sum(foodDistance) if sum(foodDistance) > 0 else 1
        def foodProximity(currentPos, foodPos, currentScore):
            newFood = sumFoodDistance(currentPos, foodPos)
            currentFood = sumFoodDistance(currentGameState.getPacmanPosition(), currentGameState.getFood().asList())
            newFood = 1/newFood
            currentFood = 1/currentFood
            if newFood > currentFood:
                currentScore += (newFood - currentFood) * 3
            else:
                currentScore -= 20
            ClosestFoodDist = closestFood(currentPos, foodPos)
            currentFoodDist = closestFood(currentGameState.getPacmanPosition(), currentGameState.getFood().asList())
            if ClosestFoodDist < currentFoodDist:
                currentScore += (ClosestFoodDist - currentFoodDist) * 3
            else:
                currentScore -= 20
            return currentScore
        return foodProximity(newPos, newFood.asList(), ghostProximity(newGhostStates, newPos, 2, successorGameState.getScore()))

def scoreEvaluationFunction(currentGameState):
    """
      This default evaluation function just returns the score of the state.
      The score is the same one displayed in the Pacman GUI.

      This evaluation function is meant for use with adversarial search agents
      (not reflex agents).
    """
    return currentGameState.getScore()



class MultiAgentSearchAgent(Agent):
    """
      This class provides some common elements to all of your
      multi-agent searchers.  Any methods defined here will be available
      to the MinimaxPacmanAgent, AlphaBetaPacmanAgent & ExpectimaxPacmanAgent.

      You *do not* need to make any changes here, but you can if you want to
      add functionality to all your adversarial search agents.  Please do not
      remove anything, however.

      Note: this is an abstract class: one that should not be instantiated.  It's
      only partially specified, and designed to be extended.  Agent (game.py)
      is another abstract class.
    """

    def __init__(self, evalFn = 'scoreEvaluationFunction', depth = '2'):
        self.index = 0 # Pacman is always agent index 0
        self.evaluationFunction = util.lookup(evalFn, globals())
        self.depth = int(depth)

class MinimaxAgent(MultiAgentSearchAgent):
    """
      Your minimax agent (question 2)
    """

    def getAction(self, gameState):
        """
          Returns the minimax action from the current gameState using self.depth
          and self.evaluationFunction.

          Here are some method calls that might be useful when implementing minimax.

          gameState.getLegalActions(agentIndex):
            Returns a list of legal actions for an agent
            agentIndex=0 means Pacman, ghosts are >= 1

          gameState.generateSuccessor(agentIndex, action):
            Returns the successor game state after an agent takes an action

          gameState.getNumAgents():
            Returns the total number of agents in the game
        """


        def PacmanValue(state, depth):
            if state.isWin() or state.isLose() or depth == self.depth:
                return self.evaluationFunction(state)
            bestValue = float("-inf")
            value = bestValue
            bestAction = Directions.STOP
            for action in state.getLegalActions(0):
                value = GhostValue(state.generateSuccessor(0,action), depth, 1)
                if value > bestValue:
                    bestValue = value
                    bestAction = action
            if depth == 0:
                return bestAction
            else: 
                return bestValue

        def GhostValue(state, depth, agent):
            if state.isWin() or state.isLose() or depth == self.depth:
                return self.evaluationFunction(state)
            ghost = agent + 1
            if agent == state.getNumAgents() - 1:
            	ghost = 0

            bestValue = float("inf")
            value = bestValue
            for action in state.getLegalActions(agent):
            	if ghost == 0:
            		if depth == self.depth -1:
            			value = self.evaluationFunction(state.generateSuccessor(agent, action))
            		else:
            			value = PacmanValue(state.generateSuccessor(agent,action),depth + 1)
            	else:
            		value = GhostValue(state.generateSuccessor(agent, action), depth, ghost)
                if value < bestValue:
                    bestValue = value
            return bestValue

        return PacmanValue(gameState, 0)


class AlphaBetaAgent(MultiAgentSearchAgent):
    """
      Your minimax agent with alpha-beta pruning (question 3)
    """

    def getAction(self, gameState):
        """
          Returns the minimax action using self.depth and self.evaluationFunction
        """
        "*** YOUR CODE HERE ***"
        def PacmanValueAB(state, depth, alpha, beta):
            if state.isWin() or state.isLose():
                return self.evaluationFunction(state)
            bestValue = float("-inf")
            value = bestValue
            bestAction = Directions.STOP
            for action in state.getLegalActions(0):
                value = GhostValueAB(state.generateSuccessor(0,action), depth, 1, alpha, beta)
                if value > bestValue:
                    bestValue = value
                    bestAction = action
                alpha = max(alpha, bestValue)
                if bestValue > beta:
                	return bestValue
            if depth == 0:
                return bestAction
            else: 
                return bestValue

        def GhostValueAB(state, depth, agent, alpha, beta):
            if state.isWin() or state.isLose():
                return self.evaluationFunction(state)
            ghost = agent + 1
            if agent == state.getNumAgents() - 1:
            	ghost = 0

            bestValue = float("inf")
            value = bestValue
            for action in state.getLegalActions(agent):
            	if ghost == 0:
            		if depth == self.depth -1:
            			value = self.evaluationFunction(state.generateSuccessor(agent, action))
            		else:
            			value = PacmanValueAB(state.generateSuccessor(agent,action),depth + 1, alpha, beta)
            	else:
            		value = GhostValueAB(state.generateSuccessor(agent, action), depth, ghost, alpha, beta)
                if value < bestValue:
                    bestValue = value
                beta = min(beta, bestValue)
                if bestValue < alpha: 
                	return bestValue

            return bestValue

        return PacmanValueAB(gameState, 0, float("-inf"), float("inf"))


class ExpectimaxAgent(MultiAgentSearchAgent):
    """
      Your expectimax agent (question 4)
    """

    def getAction(self, gameState):
        """
          Returns the expectimax action using self.depth and self.evaluationFunction

          All ghosts should be modeled as choosing uniformly at random from their
          legal moves.
        """
        value = float("-inf")
        meilleureAction = Directions.STOP
        for action in gameState.getLegalActions(0):
            tmp = self.GhostsValueE(gameState.generateSuccessor(0, action), 0, 1)
            if tmp > value and action != Directions.STOP:
                value = tmp
                meilleureAction = action
        return meilleureAction

    def PacmanValueE(self, state, depth, agent):
        if state.isWin() or state.isLose() or depth == self.depth:
            return self.evaluationFunction(state)
        value = float("-inf")
        for action in state.getLegalActions(agent):
            value = max(value, self.GhostsValueE(state.generateSuccessor(agent, action), depth, agent + 1))
        return value

    def GhostsValueE(self, state, depth, agent):
        if state.isWin() or state.isLose() or depth == self.depth:
            return self.evaluationFunction(state)
        value = 0
        for action in state.getLegalActions(agent):
            if agent == state.getNumAgents() - 1:
                value += self.PacmanValueE(state.generateSuccessor(agent, action), depth + 1, 0)
            else:
                value += self.GhostsValueE(state.generateSuccessor(agent, action), depth, agent + 1)
        value = value / len(state.getLegalActions(agent))
        return value 

def betterEvaluationFunction(currentGameState):
    """
      Your extreme ghost-hunting, pellet-nabbing, food-gobbling, unstoppable
      evaluation function (question 5).

      DESCRIPTION: <write something here so we know what you did>
    """
    "*** YOUR CODE HERE ***"
    util.raiseNotDefined()

# Abbreviation
better = betterEvaluationFunction

class ContestAgent(MultiAgentSearchAgent):
    """
      Your agent for the mini-contest
    """

    def getAction(self, gameState):
        """
          Returns an action.  You can use any method you want and search to any depth you want.
          Just remember that the mini-contest is timed, so you have to trade off speed and computation.

          Ghosts don't behave randomly anymore, but they aren't perfect either -- they'll usually
          just make a beeline straight towards Pacman (or away from him if they're scared!)
        """
        "*** YOUR CODE HERE ***"
        util.raiseNotDefined()

