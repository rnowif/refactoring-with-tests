# Refactoring legacy code using tests as a tool

Live coding performed at SoCraTesFr'17.
The final refactoring built during the session is on the `refacto/socrates` branch.

The main idea is to duplicate the method to refactor and create a test that will compare the behaviour of the new method and the legacy one for every possible inputs. Once this test setup, the new method can be refactored without any risk of regression.
