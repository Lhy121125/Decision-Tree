/*
 * ReadMe.txt
 * Contributor: Ray Zeng(Tianrui) & Nick Luo(Haiyu)
 * All group members were present and contributing during all work on this project.
 * We have neither received nor given any unauthorized aid in this assignment.
 */
Name: Tianrui Zeng and Nick Luo
We added a new file called TestClassiferMyDataSet.java to run on our own dataset train-myDataSet.txt and test-myDataSet.txt

Part2a

set1 big
Run TestClassifier.java with run configurations. It met the expected result



Positive examples correct: 100 out of 100
Negative examples correct: 100 out of 100


Feature 7 = True:Positive
Feature 7 = False:
	Feature 3 = True:Positive
	Feature 3 = False:
		Feature 4 = True:Positive
		Feature 4 = False:Negative

==========================================
set2 big

The accuracy of the model on set2 is lower than the accuracy of set1. This could be partly due to the fact set2 contains noise wherese set1 does not.

Positive examples correct: 98 out of 100
Negative examples correct: 95 out of 100


Feature 4 = True:
	Feature 8 = True:Positive
	Feature 8 = False:
		Feature 0 = True:
			Feature 2 = True:
				Feature 1 = True:
					Feature 6 = True:Positive
					Feature 6 = False:Positive
				Feature 1 = False:
					Feature 6 = True:
						Feature 3 = True:Positive
						Feature 3 = False:Negative
					Feature 6 = False:Positive
			Feature 2 = False:
				Feature 3 = True:
					Feature 9 = True:Positive
					Feature 9 = False:
						Feature 7 = True:Positive
						Feature 7 = False:Negative
				Feature 3 = False:Positive
		Feature 0 = False:Positive
Feature 4 = False:
	Feature 3 = True:
		Feature 6 = True:Positive
		Feature 6 = False:
			Feature 2 = True:Positive
			Feature 2 = False:
				Feature 0 = True:Positive
				Feature 0 = False:
					Feature 5 = True:Negative
					Feature 5 = False:Positive
	Feature 3 = False:
		Feature 7 = True:
			Feature 0 = True:
				Feature 6 = True:
					Feature 8 = True:
						Feature 5 = True:Positive
						Feature 5 = False:Positive
					Feature 8 = False:Negative
				Feature 6 = False:Positive
			Feature 0 = False:Positive
		Feature 7 = False:Negative
=======================================================================================
Part2b
Result after running TestClassifierHepatitis.java:
88 14

Feature 14 = True:
	Feature 19 = True:
		Feature 9 = True:
			Feature 7 = True:Positive
			Feature 7 = False:
				Feature 3 = True:
					Feature 0 = True:Negative
					Feature 0 = False:
						Feature 4 = True:Positive
						Feature 4 = False:Negative
				Feature 3 = False:Positive
		Feature 9 = False:Negative
	Feature 19 = False:
		Feature 15 = True:
			Feature 1 = True:
				Feature 6 = True:Positive
				Feature 6 = False:
					Feature 8 = True:
						Feature 9 = True:
							Feature 12 = True:
								Feature 16 = True:Positive
								Feature 16 = False:
									Feature 4 = True:Positive
									Feature 4 = False:Negative
							Feature 12 = False:Negative
						Feature 9 = False:Positive
					Feature 8 = False:Positive
			Feature 1 = False:Positive
		Feature 15 = False:
			Feature 8 = True:Positive
			Feature 8 = False:Negative
Feature 14 = False:
	Feature 21 = True:Negative
	Feature 21 = False:Positive
Positive examples correct: 38 out of 42 (percentage = 90%)
Negative examples correct: 7 out of 10  (percentage = 70%)
===============================================================
Part3
Run TestClassifierMyDataSet.java:
   This dataset helps HR predict what kind of candidate would stay at the company after the training. If the employee stayed, his/her data would be labelledas  True.Otherwise, his/her data would be labelled as False. This model could help HR choose the candidate who would really like to work for the company. The features involved are age, gender, experience in the industry, the city the person is from, his/her education background. The data is from https://www.kaggle.com/arashnic/hr-analytics-job-change-of-data-scientists. We processed some continous variables into binary variables through feature engineering. Below are results of our model.
50 50

Feature 1 = True:
	Feature 0 = True:
		Feature 8 = True:
			Feature 5 = True:
				Feature 3 = True:
					Feature 6 = True:
						Feature 7 = True:
							Feature 2 = True:
								Feature 9 = True:Positive
								Feature 9 = False:Positive
							Feature 2 = False:Negative
						Feature 7 = False:
							Feature 2 = True:Negative
							Feature 2 = False:Positive
					Feature 6 = False:Positive
				Feature 3 = False:Negative
			Feature 5 = False:Positive
		Feature 8 = False:Negative
	Feature 0 = False:
		Feature 6 = True:
			Feature 5 = True:
				Feature 8 = True:
					Feature 7 = True:
						Feature 9 = True:Negative
						Feature 9 = False:
							Feature 3 = True:Negative
							Feature 3 = False:Negative
					Feature 7 = False:
						Feature 2 = True:
							Feature 3 = True:
								Feature 9 = True:Negative
								Feature 9 = False:Negative
							Feature 3 = False:Negative
						Feature 2 = False:Negative
				Feature 8 = False:Negative
			Feature 5 = False:
				Feature 3 = True:
					Feature 2 = True:
						Feature 7 = True:
							Feature 9 = True:Negative
							Feature 9 = False:Negative
						Feature 7 = False:Negative
					Feature 2 = False:
						Feature 7 = True:
							Feature 9 = True:Positive
							Feature 9 = False:Negative
						Feature 7 = False:Positive
				Feature 3 = False:Positive
		Feature 6 = False:Negative
Feature 1 = False:
	Feature 9 = True:
		Feature 5 = True:
			Feature 0 = True:
				Feature 2 = True:
					Feature 7 = True:Positive
					Feature 7 = False:Positive
				Feature 2 = False:
					Feature 7 = True:Negative
					Feature 7 = False:Positive
			Feature 0 = False:Negative
		Feature 5 = False:Positive
	Feature 9 = False:Positive
Positive examples correct: 32 out of 50(percentage: 64%)
Negative examples correct: 26 out of 50(percentage: 52%)

It appears that this model is relatively good at predicting positive examples and poor at predicting negative examples. The first two important features(feature 0 and feature1) are about the development index of the city the candidate is from. It appears that if the candidate is from a big city, he is more likely to stay. The third important feature is feature8. It means that this person were from a big company, he would be more likely to stay. The fourth important feature is education background, and the fifth important feature is relvevant experience. It appears that if a person has a graduate degree and has relevant experience before, he/she is more likely to stay at the company. Other features, such as gender and discipline, are less decisive factors.
