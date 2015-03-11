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
        valeurMax = float("-inf")
        prochaineAction = Directions.STOP
        for action in gameState.getLegalActions(0):
            tmp = self.GhostsValue(gameState.generateSuccessor(0, action), 0, 1)
            if tmp > valeurMax and action != Directions.STOP:
                valeurMax = tmp
                prochaineAction = action
        return prochaineAction

    def PacmanValue(self, state, depth, agent):
        if state.isWin() or state.isLose() or depth == self.depth:
            return self.evaluationFunction(state)
        valeurMax = float("-inf")
        for action in state.getLegalActions(0):
            valeurMax = max(valeurMax, self.GhostsValue(state.generateSuccessor(agent,action), depth, agent + 1))
        return valeurMax

    def GhostsValue(self, state, depth, agent):
        if state.isWin() or state.isLose() or depth == self.depth:
            return self.evaluationFunction(state)
        valeurMin = float("inf")
        for action in state.getLegalActions(agent):
            if agent == state.getNumAgents() - 1:
                valeurMin = min(valeurMin, self.PacmanValue(state.generateSuccessor(agent, action), depth + 1, 0))
            else:
                valeurMin = min(valeurMin, self.GhostsValue(state.generateSuccessor(agent, action), depth, agent + 1))
        return valeurMin 
               

class AlphaBetaAgent(MultiAgentSearchAgent):
    """
      Your minimax agent with alpha-beta pruning (question 3)
    """

    def getAction(self, gameState):
        """
          Returns the minimax action using self.depth and self.evaluationFunction
        """
        alpha = float("-inf")
        beta = float("inf")
        return self.PacmanValue(gameState, 0, 0, alpha, beta)

    def PacmanValue(self, state, depth, agent, alpha, beta):
        if state.isWin() or state.isLose() or depth == self.depth:
            return self.evaluationFunction(state)
        valeurMax = float("-inf")
        valeur = valeurMax
        prochaineAction = Directions.STOP
        for action in state.getLegalActions(0):
            valeur = self.GhostsValue(state.generateSuccessor(0,action), depth, 1, alpha, beta)
            if valeur > valeurMax:
                valeurMax = valeur
                prochaineAction = action
            alpha = max(alpha, valeurMax)
            if valeurMax > beta:
                return valeurMax
        if depth == 0:
            return prochaineAction
        else: 
            return valeurMax

    def GhostsValue(self, state, depth, agent, alpha, beta):
        if state.isWin() or state.isLose() or depth == self.depth:
            return self.evaluationFunction(state)
        valeurMin = float("inf")
        for action in state.getLegalActions(agent):
            if agent == state.getNumAgents() - 1:
                valeurMin = min(valeurMin, self.PacmanValue(state.generateSuccessor(agent, action), depth + 1, 0, alpha, beta))
            else:
                valeurMin = min(valeurMin, self.GhostsValue(state.generateSuccessor(agent, action), depth, agent + 1, alpha, beta))
            beta = min(beta, valeurMin)
            if valeurMin < alpha: 
                return valeurMin
        return valeurMin 

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
        valeurMax = float("-inf")
        prochaineAction = Directions.STOP
        for action in gameState.getLegalActions(0):
            tmp = self.GhostsValue(gameState.generateSuccessor(0, action), 0, 1)
            if tmp > valeurMax and action != Directions.STOP:
                valeurMax = tmp
                prochaineAction = action
        return prochaineAction

    def PacmanValue(self, state, depth, agent):
        if state.isWin() or state.isLose() or depth == self.depth:
            return self.evaluationFunction(state)
        valeurMax = float("-inf")
        for action in state.getLegalActions(agent):
            valeurMax = max(valeurMax, self.GhostsValue(state.generateSuccessor(agent, action), depth, agent + 1))
        return valeurMax

    def GhostsValue(self, state, depth, agent):
        if state.isWin() or state.isLose() or depth == self.depth:
            return self.evaluationFunction(state)
        valeurMin = 0
        for action in state.getLegalActions(agent):
            if agent == state.getNumAgents() - 1:
                valeurMin = valeurMin + self.PacmanValue(state.generateSuccessor(agent, action), depth + 1, 0)
            else:
                valeurMin = valeurMin + self.GhostsValue(state.generateSuccessor(agent, action), depth, agent + 1)
        valeurMin = valeurMin / len(state.getLegalActions(agent))
        return valeurMin 

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

