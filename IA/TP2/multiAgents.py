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

#Frank Chassing - 14 153 710 - frank.chassing@usherbrooke.ca
#Amandine Fouillet - 14 130 638 - amandine.fouillet@usherbrooke.ca

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

        # return the closest food of the current position
        def closestFood(currentPos, foodPos):
            foodDistance = []
            " We put all the food positions in a tab and we calculate the manhattan distance between these positions and the current position"
            for food in foodPos :
                foodDistance.append(util.manhattanDistance(food, currentPos))
            " we return the smallest distance of the tab"
            return min(foodDistance) if len(foodDistance) > 0 else 1

        # return the current score
        def ghostProximity(ghostState, currentPos, distanceMin, currentScore):
            for ghost in ghostState :
                " if the manhattan distance between ghosts and the current position is less than the minimal distance accepted, we decrease the current score "
                if util.manhattanDistance(ghost.getPosition(), currentPos) <= distanceMin :
                    currentScore = currentScore - 30
            return currentScore

        # return the sum of all the food distances
        def sumFoodDistance(currentPos, foodPos):
            foodDistance = []
            for food in foodPos :
                " We put all the manhattan distance between the current position and all food positions in a tab "
                foodDistance.append(util.manhattanDistance(food, currentPos))
            " We return the sum of the distances"
            return sum(foodDistance) if sum(foodDistance) > 0 else 1

        # return the score updated
        def foodProximity(currentPos, foodPos, currentScore):
            "We take the sum of the food distances to the current position "
            newFood = sumFoodDistance(currentPos, foodPos)
            "We take the sum of the food distances to the current position at the current game state "
            currentFood = sumFoodDistance(currentGameState.getPacmanPosition(), currentGameState.getFood().asList())
            "We calculates the inverse "
            newFood = 1/newFood
            currentFood = 1/currentFood
            " if the new food is better, we increase the score "
            if newFood > currentFood:
                currentScore += (newFood - currentFood) * 3
            else:
                "else we decreases the score"
                currentScore -= 20
            "We find the new closest distance food and the closest distance food in the current game"
            ClosestFoodDist = closestFood(currentPos, foodPos)
            currentFoodDist = closestFood(currentGameState.getPacmanPosition(), currentGameState.getFood().asList())
            " if the new closest distance food is smallest, we increase the score "
            if ClosestFoodDist < currentFoodDist:
                currentScore += (ClosestFoodDist - currentFoodDist) * 3
            else:
                "Else we decrease the score"
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

        "We initialize the max value to -inf to be sure that we'll find a superior value"
        valeurMax = float("-inf")

        "For each legal actions for pacman, we calculate the min value for the ghosts"
        for action in gameState.getLegalActions(0):
            valeur = self.GhostsValue(gameState.generateSuccessor(0, action), 0, 1)
            "if the value is superior to the max value, the max value become the actual value and the next action is set to the actual action"
            if valeur > valeurMax:
                valeurMax = valeur
                prochaineAction = action
        "Finaly, we return the best next action"
        return prochaineAction

    def PacmanValue(self, state, depth, agent):
        "If the game is over we return the evaluation function"
        if state.isWin() or state.isLose() or depth == self.depth:
            return self.evaluationFunction(state)

        "We initialize the max value to -inf to be sure that we'll find a superior value"
        valeurMax = float("-inf")

        "For each legal actions for pacman, we search the max value's of the min values for the ghosts"
        for action in state.getLegalActions(agent):
            valeurMax = max(valeurMax, self.GhostsValue(state.generateSuccessor(agent,action), depth, agent + 1))

        "We return the found value"
        return valeurMax

    def GhostsValue(self, state, depth, agent):
        "If the game is over we return the evaluation function"
        if state.isWin() or state.isLose() or depth == self.depth:
            return self.evaluationFunction(state)

        "We initialize the min value to +inf to be sure that we'll find an inferior value"
        valeurMin = float("inf")

        "For each legal actions for the ghosts, we search the min value's of the max values for pacman"
        for action in state.getLegalActions(agent):
            "If pacman is the agent, we call the PacmanValue function"
            if agent == state.getNumAgents() - 1:
                valeurMin = min(valeurMin, self.PacmanValue(state.generateSuccessor(agent, action), depth + 1, 0))
            else:
                "Else we call back the GhostsValue function to search a smaller value"
                valeurMin = min(valeurMin, self.GhostsValue(state.generateSuccessor(agent, action), depth, agent + 1))

        "We return the found value"
        return valeurMin 
               

class AlphaBetaAgent(MultiAgentSearchAgent):
    """
      Your minimax agent with alpha-beta pruning (question 3)
    """

    def getAction(self, gameState):
        """
          Returns the minimax action using self.depth and self.evaluationFunction
        """
        "We initialize the alpha value to -inf to be sure that we'll found a superior value"
        alpha = float("-inf")
        "We initialize the beta value to +inf to be sure that we'll found an inferior value"
        beta = float("inf")

        return self.PacmanValue(gameState, 0, 0, alpha, beta)

    def PacmanValue(self, state, depth, agent, alpha, beta):
        "If the game is over we return the evaluation function"
        if state.isWin() or state.isLose() or depth == self.depth:
            return self.evaluationFunction(state)

        "We initialize the max value to -inf to be sure that we'll find a superior value"
        valeurMax = float("-inf")

        "We initialize the actual value to -inf to be sure that we'll find a superior value"
        valeur = valeurMax

        "For each legal actions for pacman, we search the max value's of the min values for the ghosts and the best next action for pacman"
        for action in state.getLegalActions(0):
            valeur = self.GhostsValue(state.generateSuccessor(0,action), depth, 1, alpha, beta)
            if valeur > valeurMax:
                valeurMax = valeur
                prochaineAction = action
            "We save the alpha value wich is the max value for pacman"
            alpha = max(alpha, valeurMax)
            "If we found a value superior to beta value, we stop the research and we return the actual best value for pacman"
            if valeurMax > beta:
                return valeurMax
        if depth == 0:
            return prochaineAction
        else: 
            return valeurMax

    def GhostsValue(self, state, depth, agent, alpha, beta):
    	"If the game is over we return the evaluation function"
        if state.isWin() or state.isLose() or depth == self.depth:
            return self.evaluationFunction(state)

        "We initialize the max value to inf to be sure that we'll find an inferior value"
        valeurMin = float("inf")

        "For each legal actions for the ghosts, we search the min value's of the max values for pacman"
        for action in state.getLegalActions(agent):
            if agent == state.getNumAgents() - 1:
            	"If pacman is the agent, we call the PacmanValue function"
                valeurMin = min(valeurMin, self.PacmanValue(state.generateSuccessor(agent, action), depth + 1, 0, alpha, beta))
            else:
            	"Else we call back the GhostsValue function to search a smaller value"
                valeurMin = min(valeurMin, self.GhostsValue(state.generateSuccessor(agent, action), depth, agent + 1, alpha, beta))
            "We save the beta value wich is the min value for the ghosts"
            beta = min(beta, valeurMin)
            "If we found a value inferior to alpha value, we stop the research and we return the actual best value for the ghosts"
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
        "We initialize the max value to -inf to be sure that we'll find a superior value"
        valeurMax = float("-inf")

        "For each legal actions for pacman, we calculate the max value"
        for action in gameState.getLegalActions(0):
            valeur = self.GhostsValue(gameState.generateSuccessor(0, action), 0, 1)
            "if the value is superior to the max value, the max value become the actual value and the next action is set to the actual action"
            if valeur > valeurMax:
                valeurMax = valeur
                prochaineAction = action
        "Finaly, we return the best next action"
        return prochaineAction

    def PacmanValue(self, state, depth, agent):
    	"If the game is over we return the evaluation function"
        if state.isWin() or state.isLose() or depth == self.depth:
            return self.evaluationFunction(state)
        "We initialize the max value to -inf to be sure that we'll find a superior value"
        valeurMax = float("-inf")
        "For each legal actions for pacman, we search the max value's of the min values for the ghosts"
        for action in state.getLegalActions(agent):
            valeurMax = max(valeurMax, self.GhostsValue(state.generateSuccessor(agent, action), depth, agent + 1))
        "We return the found value"
        return valeurMax

    def GhostsValue(self, state, depth, agent):
    	"If the game is over we return the evaluation function"
        if state.isWin() or state.isLose() or depth == self.depth:
            return self.evaluationFunction(state)
        "We initialize the min value"
        valeurMin = 0

        "For each legal actions for the ghosts, we search the min value's of the max values for pacman"
        for action in state.getLegalActions(agent):
            if agent == state.getNumAgents() - 1:
                "If pacman is the agent, we call the PacmanValue function"
                valeurMin = valeurMin + self.PacmanValue(state.generateSuccessor(agent, action), depth + 1, 0)
            else:
            	"Else we call back the GhostsValue function to search a smaller value"
                valeurMin = valeurMin + self.GhostsValue(state.generateSuccessor(agent, action), depth, agent + 1)
        "We calculate the probabilty"
        valeurMin = valeurMin / len(state.getLegalActions(agent))
        return valeurMin 

def betterEvaluationFunction(currentGameState):
    """
      Your extreme ghost-hunting, pellet-nabbing, food-gobbling, unstoppable
      evaluation function (question 5).

      DESCRIPTION: <write something here so we know what you did>
    """
    "*** YOUR CODE HERE ***"
    newPos = currentGameState.getPacmanPosition()
    newFood = currentGameState.getFood()
    newGhostStates = currentGameState.getGhostStates()
    newScaredTimes = [ghostState.scaredTimer for ghostState in newGhostStates]
    
    score = 0
    ghostDistTab = []
    
    for scareTimeData in newScaredTimes:
        score += scareTimeData
    
    foodDistTab = []
    
    # Return a tab with the coordonnates next to the position in argument
    def foodProximity(foodPosTab):
        foodDistTab = []
        foodDistTab.append((foodPosTab[0]-1,foodPosTab[1])) # (x-1,y)
        foodDistTab.append((foodPosTab[0],foodPosTab[1]-1)) # (x,y-1)
        foodDistTab.append((foodPosTab[0],foodPosTab[1]+1)) # (x,y+1)
        foodDistTab.append((foodPosTab[0]+1,foodPosTab[1])) # (x+1,y)
        return foodDistTab
     
    neighborFood = 0
    wall = currentGameState.getWalls().asList()
    food = newFood.asList()
    for foodData in food:
        "We take the neighbors of each foodData of food in the current game"
        neighborsData = foodProximity(foodData)
        for foodProximityData in neighborsData:
            "if the neighbor is not a wall or if it's not already a food"
            if foodProximityData not in wall and foodProximityData not in food:
                "We incremente the number of neighbor of food"
                neighborFood = neighborFood + 1
        "We put the manhattan distance between the pacman position and the position of the food in a tab"
        foodDistTab += [manhattanDistance(newPos,foodData)]
    
    "We inverse the minimum of the tab to have the biggest inverse value"
    inverseFoodDistTab = 0
    if len(foodDistTab) > 0:
        inverseFoodDistTab = 1.0/(min(foodDistTab))
    
    "We put the manhattan distance between the pacman position and the position of the ghost in a tab"
    for ghost in newGhostStates:
        ghostDistTab += [manhattanDistance(ghost.getPosition(),newPos)]


    score = score + (min(ghostDistTab)*((inverseFoodDistTab**4)))
    score = score + currentGameState.getScore()-(float(neighborFood)*4.5)
    return score

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