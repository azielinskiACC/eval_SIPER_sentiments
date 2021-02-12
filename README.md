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
* **proquest-skipgrams.py:** Code to learn the concept embeddings to find out which are distal or proximal linkages.
* **obtain_innovation_years_proquest_msearch_chunks_only_hits_filtered.py:** Looks up each dyad and dumps when it was first introduced, introduced thesis ID and future uptakes for that dyad.
* **merge_innovations_uptakes_files.py:** Aggregates uptakes etc. by thesis ID.
* **merge_innovations_uptakes_files.py:** Runs Structural Topic Models at specified range of K (50-1000 in the paper).
* **concepts_k500_50.R:** Extracts concepts from the structural topic model output, the number of words, topics, and FREX weighing can be adjusted in the code to get at the differend K/FREX scenarios.

## Logic for computing novelty and uptake
We seek to compute the sentiment for each sentence for each document language-wise. To find the uptake of an evaluation concept, we first find the . This logic an be implemented in many ways based on your needs. Below we point to one such implementation that was suited for compute infrastructure. Note that the below implementation by its design requires customization because of the heavy setup needed. We chose this approach because envisioned future projects that needed this anyways where we needed efficient ways of identifying evaluation cartegories a given set of terms.

* GetSentencesFittingPerformanceCategories.java
196363 Hits (sentences) have been extracted, containing one or more hits. Am example is displayed below:

| Filename  | #Hits  | Terms | Sentence |
| :------------ |:-------:| -----:|-----------------------------------------------------------------------------------------------------:|
| 2006-304.cermtxt    | 6 |institution, use, new, ways, research, results | Has SPARK helped your institution discover and use new ways of communicating research results to a broader audience? |
	


We implement the above by ... In particular, we r. The first is 
We had to do this in chunks because of restricted memory/compute requirements. The outputs of these are then just aggregated across each thesis to obtain (a) The number of links (b) the total uptakes of dyads and the (c) mean distal score introduced by the thesis.

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


