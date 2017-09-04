/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.massedynamic.eclipse.core.actions;

import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.forms.editor.FormEditor;

/**
 * Base class for "Open" actions.
 * 
 * @author Mark Masse
 */
public abstract class BaseOpenAction extends BaseAction {

    private static final String ACTION_TEXT = "Open";
    private static final String ACTION_TOOL_TIP_TEXT = ACTION_TEXT;

    private IOpenListener _OpenListener;

    public BaseOpenAction() {
        this(InputType.STRUCTURED_SELECTION);
    }

    public BaseOpenAction(InputType inputType) {
        super(inputType);
        setText(ACTION_TEXT);
        setToolTipText(ACTION_TOOL_TIP_TEXT);

        _OpenListener = new IOpenListener() {

            @Override
            public void open(OpenEvent event) {
                run();
            }
        };
    }

    protected abstract String getAssociatedEditorId(Object object);

    protected abstract IEditorInput getAssociatedEditorInput(String editorId, Object object);

    protected String getAssociatedEditorPageId(String editorId, Object object) {
        return null;
    }

    protected String getOpenErrorMessage(Object object, IEditorInput input, String editorId, Exception e) {
        return "Failed to open '" + object + "'.";
    }

    @Override
    protected void hookViewer(Viewer viewer) {
        super.hookViewer(viewer);

        if (viewer instanceof StructuredViewer) {
            ((StructuredViewer) viewer).addOpenListener(_OpenListener);
        }
    }

    protected IEditorPart open(Object object, IEditorInput input, String editorId, String pageId) throws Exception {
        IEditorPart editor = openEditor(input, editorId, true);
        if (pageId != null && editor instanceof FormEditor) {
            ((FormEditor) editor).setActivePage(pageId);
        }

        return editor;
    }

    @Override
    public void runWithObject(Object object) throws Exception {

        String editorId = getAssociatedEditorId(object);
        if (editorId == null) {
            return;
        }

        IEditorInput input = getAssociatedEditorInput(editorId, object);
        if (input == null) {
            return;
        }

        String pageId = getAssociatedEditorPageId(editorId, object);

        try {
            open(object, input, editorId, pageId);
        }
        catch (Exception e) {
            throw new Exception(getOpenErrorMessage(object, input, editorId, e), e);
        }
    }

    @Override
    protected void unhookViewer(Viewer viewer) {
        super.unhookViewer(viewer);

        if (viewer instanceof StructuredViewer) {
            ((StructuredViewer) viewer).removeOpenListener(_OpenListener);
        }
    }

}
