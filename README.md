# The Sentiment Analysis for SIPER Evaluation Reports
This repository contains code and data associated with “SIPER Project in ISDEC.” PDF can be found here

If you use any of the code or ideas presented here, please cite our paper:
* TODO

## In a nutshell
By analyzing data from the SIPER repository for sentiment, this paper finds that ?? can be predicted best with algorithm Y. We explore and evaluate diverse methods for modeling sentiment:
* Hand-built feature functions with (mostly linear) classifiers
* Dense feature representations derived from VSMs as we built them in the previous unit
* Recurrent neural networks (RNNs)
* Tree-structured neural networks
Begin discussing and implementing responsible methods for hyperparameter optimization and classifier assessment and comparison.

The unit is built around the Stanford Sentiment Treebank (SST), a widely-used resource for evaluating supervised NLU models, and one that provides rich linguistic representations.

The state-of-the-art for the SST seems to be around 88% accuracy for the binary problem and 48% accuracy for the five-class problem.



## Code
With the provided code the sentiment and confidence can be constructed from the SIPER evaluation reports.

* **LanguageDetectionTIKA.java:** For classification of documents based on n-grams and word sets based on TIKA, a language detection tool for 18 languages. Files are read from a directory and split into subdirecrtory by language. [https://www.tutorialspoint.com/tika/tika_language_detection.htm]
* **GetSentencesFittingPerformanceCategories.java:** Extracts all sentences from a directory of SIPER documents that have at least one term in common with the performance thesaurus.
* **SentimentEvaluationSIPER.java:** Code to calculate the sentiment of a sentence. Only positive and negative statements are relevant, while neutral sentences will not be considered during the further processing.


## Logic for computing the Polarity of the Sentences
We seek to compute the sentiment for each sentence for each document language-wise. To find the uptake of an evaluation concept, we first find them on a simple keyword analysis  as a given set of terms. This is a baseline and can be implemented in many ways based on your needs. Below we point to one such implementation that was suited for compute a sample of evaluation reports. Note that the below implementation requires customization because of error-prone pdf-to-text conversion. We chose this approach because it considers negation and modifier/argument structures that are needed for identifying sentiments of evaluation cartegories.

* GetSentencesFittingPerformanceCategories.java
196363 Hits (sentences) have been extracted, containing one or more hits. Am example is displayed below:

| Filename  | #Hits  | Terms | Sentence |
| :------------ |:-------:| -----:|-----------------------------------------------------------------------------------------------------:|
| 2006-304.cermtxt    | 6 |institution, use, new, ways, research, results | Has SPARK helped your institution discover and use new ways of communicating research results to a broader audience? |
| 2015-478.cermtxt    | 7 | aims, include, developing, integrating, design, management, fit | Given that the aims of the Design Service include developing the capability of businesses by integrating design and innovation into business management systems and strategies, this is seen to fit with Government strategies.| 
| 2017-405.cermtxt    | 2 | list, transparent | No list of consultees is given so there is no transparent justification for this assumptions. | 


	
* SentimentEvaluationsSIPER.java
Sentiment Analysis for the file above, including 196363 Hits (sentences). Am example is displayed below:

| Sentiment   |  Sentence                                                             |           Parse Tree            | 
| :---------- |:----------------------------------------------------------------------|:-------------------------------:|
| Moderate    |	Given the current economic conditions, the risks of not achieving the full public sector legacy targets are considered to be moderate for all three funds | ![foxdemo6](https://github.com/azielinskiACC/eval_SIPER_sentiments/blob/main/Parse1.png) |
| Positive    |	Both the review and the evaluation of the NANOMAT programme stressed the need for more generous long-term funding and that increased efforts were needed to harvest commercial results in the form of patents, new companies and innovations to a greater extent than was achieved under the NANOMAT programme. | ![foxdemo5](https://github.com/azielinskiACC/eval_SIPER_sentiments/blob/main/Parse2.png) |


Each tree will have a sentiment score from "STRONG_NEGATIVE", "WEAK_NEGATIVE", "NEUTRAL", "WEAK_POSITIVE", "STRONG_POSITIVE"

| Sentiment   |  Sentence                                                             |   Label  (Manually assigned)          | 
| :---------- |:----------------------------------------------------------------------|:-------------------------------:|
| Negative     | There are very few pockets of activity among the research programs that do not meet the organization's high standard of excellence. |   NEGATIVE  |
| Negative     | In the absence of a logic model, it is difficult to ascertain whether or not the CRC is meeting its expected outcomes.  | NEUTRAL   |
| Neutral      | There is some evidence to demonstrate that the CRC is making progress towards its stated objectives. | POSITIVE  |
| Neutral      | However, the program needs to improve its performance measurement. | NEGATIVE  |
| Neutral      | Multiple lines of evidence suggest that clients benefit from the CRC's unbiased advice in terms of scientific findings, technical solutions and IP licensing. | POSITIVE   | 
| Negative     | However, a more robust client/business development function is required. | NEGATIVE  | 
| Negative     | It is currently not possible to track expenditures and resource use on individual research projects, largely due to the absence of a time tracking system for researchers. |  NEGATIVE | 
| Negative     | This makes it difficult to determine the true cost of services provided to clients.| NEGATIVE  | 
| Neutral      | These systems are in use in other Government of Canada research centres. | NEUTRAL   |
| Neutral      | The CRC's governance model is in line with Treasury Board policies and the organization is maximizing the recovery of costs.| NEUTRAL   |
| Negative     | However, the governance model is not optimal as it detracts from the organization's ability to focus on its stated research mission.| NEGATIVE  | 
| Negative     | Further, the campus is in need of major investments to head off mounting health and safety issues which may put the governance model at risk.|  NEGATIVE  | 
| Neutral      | The corporate costs are relatively high compared with other federal research centres, however, some of the organization's corporate services are allocated to supporting tenant services.|  NEGATIVE  || 
| Positive     | Efficiencies in corporate costs may be gained if some functions were consolidated, centralized, or outsourced.|  NEUTRAL   | 
| Negative     | This makes it difficult to determine the true cost of services provided to clients.| NEGATIVE  |  
| Negative     | Research activities at the CRC are not functioning at optimal levels, largely due to an organizational structure that is based on historical precedents and legacy responsibilities.| NEGATIVE  | 







## Data
### For raw data 
* SIPER Policy Evaluation Database: https://www.risis2.eu/2019/08/26/siper-worldwide-policy-evaluation-database/
Figure 2.
![foxdemo](https://github.com/azielinskiACC/eval_SIPER_sentiments/blob/main/SiperLanguagesData.png)
### For Performance Ontology/Thesaurus: 
Figure 3.
![foxdemo2](https://github.com/azielinskiACC/eval_SIPER_sentiments/blob/main/PerformanceCategories.png)


### Papers:
* [https://nlp.stanford.edu/sentiment/]
* Recursive Deep Models for Semantic Compositionality Over a Sentiment Treebank (2013) Richard Socher, Alex Perelygin, Jean Y. Wu, Jason Chuang, Christopher D. Manning, Andrew Y. Ng and Christopher Potts (EMNLP) [https://nlp.stanford.edu/~socherr/EMNLP2013_RNTN.pdf] 


