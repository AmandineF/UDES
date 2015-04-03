# valueIterationAgents.py
# -----------------------
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


import mdp, util

from learningAgents import ValueEstimationAgent

class ValueIterationAgent(ValueEstimationAgent):
    """
        * Please read learningAgents.py before reading this.*

        A ValueIterationAgent takes a Markov decision process
        (see mdp.py) on initialization and runs value iteration
        for a given number of iterations using the supplied
        discount factor.
    """
    def __init__(self, mdp, discount = 0.9, iterations = 100):
        """
          Your value iteration agent should take an mdp on
          construction, run the indicated number of iterations
          and then act according to the resulting policy.

          Some useful mdp methods you will use:
              mdp.getStates()
              mdp.getPossibleActions(state)
              mdp.getTransitionStatesAndProbs(state, action)
              mdp.getReward(state, action, nextState)
              mdp.isTerminal(state)
        """
        self.mdp = mdp
        self.discount = discount
        self.iterations = iterations
        self.values = util.Counter() # A Counter is a dict with default 0

        stateSet = self.mdp.getStates()
	
	#loop of the number of iterations
        for i in range(iterations):
          #We create a temporary vector
	  tmp = util.Counter()

          for state in stateSet:
	    #Initialization of the result to (-infinity)
            res = float("-inf")
	    #for each state we take the possible actions
            actionSet = mdp.getPossibleActions(state)

            for action in actionSet:
	      ResFct = 0
	      #We take the next state corresponding to the action performed from the state 
              nextStateSet = self.mdp.getTransitionStatesAndProbs(state, action)
              

              for nextState in nextStateSet:
		#For each next state, we get the reward corresponding
                reward = self.mdp.getReward(state, action, nextState[0])
		#We make the equation of the iteration value function
                ResFct += (reward + discount*self.values[nextState[0]])*nextState[1]

	      #we take the best value between the old and the new value
              res = max(res, ResFct)
	    
	    #if the result isn't (-infinity), we put the result in the temporary vector
            if res != float("-inf"):
              tmp[state] = res
            
	  #finally, we change values with the values contained in the temporary vector	  
          for state in stateSet:
            self.values[state] = tmp[state]


    def getValue(self, state):
        """
          Return the value of the state (computed in __init__).
        """
        return self.values[state]


    def computeQValueFromValues(self, state, action):
        """
          Compute the Q-value of action in state from the
          value function stored in self.values.
        """
	#We get the set of the transitions corresponding to the state and the action given
        nextStateSet = self.mdp.getTransitionStatesAndProbs(state, action)
        ResFct = 0
        for nextState in nextStateSet:
	  #for each transition, we get the reward and we make the equation of the iteration value function
          reward = self.mdp.getReward(state, action, nextState[0])
          ResFct += (reward + self.discount*self.values[nextState[0]])*nextState[1]

        return ResFct

    def computeActionFromValues(self, state):
        """
          The policy is the best action in the given state
          according to the values currently stored in self.values.

          You may break ties any way you see fit.  Note that if
          there are no legal actions, which is the case at the
          terminal state, you should return None.
        """
	#Initialization of the result to (-infinity) and the best action to "None"
	bestAction = None        
	res = float("-inf")
        
	#We get the set of actions corresponding to the state given
        actionSet = self.mdp.getPossibleActions(state)

        for action in actionSet:
	  #for each action, we call the previous function to get the result of the equation of the iteration value function
          tmp = self.computeQValueFromValues(state, action)
	  #if the result is better, we change the final result and the best action
          if tmp > res:
            res = tmp
            bestAction = action
		
        return bestAction

    def getPolicy(self, state):
        return self.computeActionFromValues(state)

    def getAction(self, state):
        "Returns the policy at the state (no exploration)."
        return self.computeActionFromValues(state)

    def getQValue(self, state, action):
        return self.computeQValueFromValues(state, action)
